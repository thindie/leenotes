package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.domain.SummaryRepository
import com.example.thindie.leenotes.domain.SummaryStep
import com.example.thindie.leenotes.domain.entities.Summary
import com.example.thindie.leenotes.presentation.features.feature_note_stats.di.NotesStatisticsScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@NotesStatisticsScope
class SummaryRepositoryImpl @Inject constructor() : SummaryRepository {
    override fun getSummary(): Flow<Summary> {
        TODO("Not yet implemented")
    }

    override fun requestPreviousSummaryStep() {
        TODO("Not yet implemented")
    }

    override fun requestNextSummaryStep() {
        TODO("Not yet implemented")
    }

    override fun setSummaryStep(step: SummaryStep) {
        TODO("Not yet implemented")
    }
}