package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.data.timeManagement.TimeOperator
import com.example.thindie.leenotes.domain.SummaryRepository
import com.example.thindie.leenotes.domain.SummaryStep
import com.example.thindie.leenotes.domain.entities.Summary
import com.example.thindie.leenotes.presentation.features.feature_note_stats.di.NotesStatisticsScope
import java.time.LocalDateTime
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

@NotesStatisticsScope
class SummaryRepositoryImpl @Inject constructor(
    private val timeOperator: TimeOperator,
    private val notesDao: NotesDao,
    private val costsDao: CostsDao,
) : SummaryRepository {

    private val summaryStep = MutableStateFlow(SummaryStep.MONTH)

    private val timeOffset = MutableStateFlow(0)

    private val summaryFlow =
        combine(
            summaryStep,
            timeOffset,
            notesDao.observeNotes(),
            costsDao.getTopCosts()
        ) { step, offset, allNotes, costs ->

            val summaryLocalDateTime = getLocalDateTime(offset, step)
            fun noteTime(noteDbModel: NoteDbModel, timeOperator: TimeOperator) = run {
                timeOperator
                    .getCurrentLocalDateTime(
                        noteDbModel.creationTimeInMillis
                    )
            }

            val notes = allNotes.filter {
                when (step) {
                    SummaryStep.DAY -> {
                        noteTime(it, timeOperator)
                            .dayOfYear ==
                                summaryLocalDateTime.dayOfYear
                    }

                    SummaryStep.MONTH -> {
                        noteTime(it, timeOperator).month == summaryLocalDateTime.month
                    }

                    SummaryStep.YEAR -> {
                        noteTime(it, timeOperator).year == summaryLocalDateTime.year
                    }

                    SummaryStep.ALL -> {
                        true
                    }
                }
            }

            val costIds = notes.map(NoteDbModel::costId)

            Summary(
                totalNotes = getNotesQuota(notes),
                totalSpent = getTotalCost(costs.filter { costIds.contains(it.id) }, isSpent = true),
                tempNotes = getNotesQuota(notes.filter { it.costId == null && it.bindingsId == null }),
                spentNotes = getNotesQuota(costs.filter { costIds.contains(it.id) && it.isBought }),
                plannedCosts = getTotalCost(costs, isSpent = false),
                topSpent = getTopCosts(notes, costs, isSpent = true, takenSize = 3),
                topPlanned = getTopCosts(notes, costs, isSpent = false, takenSize = 3),
                summaryMonth = timeOperator.getCurrent(
                    localDateTime = summaryLocalDateTime,
                    pattern = "MMM, yyyy"
                ),
                summaryDay = timeOperator.getCurrent(
                    localDateTime = summaryLocalDateTime,
                    pattern = "E, dd MMM, yyyy"
                ),
                summaryYear = timeOperator.getCurrent(
                    localDateTime = summaryLocalDateTime,
                    pattern = "yyyy"
                ),
                isCurrentTimeSummary = offset == 0,
                step = step
            )
        }

    private fun getLocalDateTime(offset: Int, step: SummaryStep): LocalDateTime {
        return when (step) {
            SummaryStep.DAY -> timeOperator.getCurrent().minusDays(offset.toLong())
            SummaryStep.MONTH -> timeOperator.getCurrent().minusMonths(offset.toLong())
            SummaryStep.YEAR -> timeOperator.getCurrent().minusYears(offset.toLong())
            SummaryStep.ALL -> timeOperator.getCurrent()
        }
    }


    override fun getSummary(): Flow<Summary> {
        return summaryFlow
    }

    override fun requestPreviousSummaryStep() {
        timeOffset.update(Int::inc)
    }

    override fun requestNextSummaryStep() {
        if (timeOffset.value > 0) {
            timeOffset.update(Int::dec)
        }
    }

    override fun setSummaryStep(step: SummaryStep) {
        summaryStep.update { step }
    }

    private fun getTotalCost(list: List<CostDbModel>, isSpent: Boolean): Double {
        return list
            .filter { it.isBought == isSpent }
            .sumOf(CostDbModel::price)
    }

    private fun <T> getNotesQuota(list: List<T>): Int {
        return list.size
    }

    private fun getTopCosts(
        list: List<NoteDbModel>,
        costList: List<CostDbModel>,
        isSpent: Boolean,
        takenSize: Int,
    ): List<String> {


        val filteredBySpent = costList
            .filter { it.isBought == isSpent }
            .sortedBy(CostDbModel::price)
            .asReversed()

        val idList = costList.map(CostDbModel::id)

        return list
            .filter { idList.contains(it.costId) }
            .map { noteDbModel ->
                noteDbModel
                    .title
                    .plus(": ")
                    .plus(filteredBySpent.lastOrNull { it.id == noteDbModel.costId }?.price)
            }
            .filter { !it.contains("null") }
            .take(takenSize)
    }


}