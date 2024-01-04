package com.example.thindie.leenotes.domain

import com.example.thindie.leenotes.domain.entities.Summary
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {
    fun getSummary(): Flow<Summary>

    fun requestPreviousSummaryStep()

    fun requestNextSummaryStep()

    fun setSummaryStep(step: SummaryStep)
}