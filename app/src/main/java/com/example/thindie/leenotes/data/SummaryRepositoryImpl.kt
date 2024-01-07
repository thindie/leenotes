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
        ) { step, offset, notes, costs ->
            Summary(
                totalNotes = getNotesQuota(notes),
                totalSpent = getTotalCost(costs, isSpent = true),
                totalTempNotes = getNotesQuota(notes.filter { it.costId == 0 && it.bindingsId == 0 }),
                totalPlannedToSpentNotes = getNotesQuota(costs.filter { !it.isBought }),
                totalPlannedCosts = getTotalCost(costs, isSpent = false),
                topSpent = getTopCosts(notes, costs, isSpent = true),
                topPlanned = getTopCosts(notes, costs, isSpent = false),
                summaryMonth = "",
                summaryDay = "",
                summaryYear = ""
            )
        }

    override fun getSummary(): Flow<Summary> {
        return summaryFlow
    }

    override fun requestPreviousSummaryStep() {
        if (timeOffset.value > 0) {
            timeOffset.update { it - 1 }
        }
    }

    override fun requestNextSummaryStep() {
        timeOffset.update { it + 1 }
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
    ): List<String> {


        val filteredBySpent = costList
            .filter { it.isBought == isSpent }
            .sortedBy { it.price }
            .asReversed()

        val idList = costList.map(CostDbModel::id)

        return list
            .filter { idList.contains(it.costId) }
            .map { noteDbModel ->
                noteDbModel
                    .title
                    .plus(" ")
                    .plus(filteredBySpent.lastOrNull { it.id == noteDbModel.costId }?.price)
            }
            .filter { !it.contains("null") }
            .take(3)
    }


}