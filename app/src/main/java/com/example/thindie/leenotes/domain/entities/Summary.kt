package com.example.thindie.leenotes.domain.entities

import com.example.thindie.leenotes.domain.SummaryStep

data class Summary(
    val totalNotes: Int,
    val totalSpent: Double,
    val tempNotes: Int,
    val spentNotes: Int,
    val plannedCosts: Double,
    val topSpent: List<String>,
    val topPlanned: List<String>,
    val summaryMonth: String,
    val summaryDay: String,
    val summaryYear: String,
    val isCurrentTimeSummary: Boolean = true,
    val step: SummaryStep,
)