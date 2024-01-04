package com.example.thindie.leenotes.domain.entities

data class Summary(
    val totalNotes: Int,
    val totalSpent: Double,
    val totalTempNotes: Int,
    val totalPlannedToSpentNotes: Int,
    val totalPlannedCosts: Double,
    val topSpent: List<String>,
    val topPlanned: List<String>,
    val monthOffset: Int = 0,
    val yearOffset: Int = 0,
    val dayOffset: Int = 0
)