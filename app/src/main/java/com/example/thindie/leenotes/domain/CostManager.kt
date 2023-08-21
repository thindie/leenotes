package com.example.thindie.leenotes.domain

interface CostManager {
   suspend fun addCost(note: Note)
   suspend fun getCostSum(): String

   suspend fun getCosts(): List<Cost>
}