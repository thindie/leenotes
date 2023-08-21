package com.example.thindie.leenotes.domain

interface CostRepository {
    suspend fun addCost(cost: Cost)
    suspend fun getCostSum(): String

    suspend fun getCosts(): List<Cost>
}