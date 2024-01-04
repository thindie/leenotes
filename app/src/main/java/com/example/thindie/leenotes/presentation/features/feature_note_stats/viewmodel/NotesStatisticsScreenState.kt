package com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel

import com.example.thindie.leenotes.domain.SummaryStep
import com.example.thindie.leenotes.domain.entities.Summary

data class NotesStatisticsScreenState(

    val step: SummaryStep = SummaryStep.MONTH,
    val summary: Summary
)
