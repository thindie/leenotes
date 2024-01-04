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
        summaryStep.combine(timeOffset) { step, offset ->
            Summary(
                totalNotes = 0,
                totalSpent = 0.0,
                totalTempNotes = 0,
                totalPlannedToSpentNotes = 0,
                totalPlannedCosts = 0.0,
                topSpent = listOf(),
                topPlanned = listOf(),
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

    private suspend fun getTopCosts(list: List<NoteDbModel>, isSpent: Boolean): List<String> {
        val topCosts = costsDao.getTopCosts(limit = 5, isSpent)

        return list
            .filter { topCosts.map(CostDbModel::id).contains(it.costId) }
            .map { noteDbModel ->
                noteDbModel
                    .title
                    .plus(" ")
                    .plus(topCosts.last { it.id == noteDbModel.costId }.price)
            }
    }


}