package com.example.thindie.leenotes.domain

interface CostManager {
    suspend fun addCost(note: Note)
    suspend fun getCostSum(): String

    suspend fun getCostSumCurrentMonth(): String

    suspend fun getCostSumPreviousMonth(): String

    suspend fun getCostsCurrentMonth(): List<Cost>

    suspend fun getCostsPreviousMonth(): List<Cost>
    suspend fun getCosts(): List<Cost>
}