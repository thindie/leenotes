package com.example.thindie.leenotes.domain

import javax.inject.Inject

class CostsInteractor @Inject constructor(
    private val costRepository: CostRepository,
    private val helper: DateFilterHelper,
) :
    CostManager {
    override suspend fun addCost(note: Note) {
        costRepository.addCost(note.map())
    }

    override suspend fun getCostSum(): String {
        return costRepository.getCostSum()
    }

    override suspend fun getCostSumCurrentMonth(): String {
        return getCostsCurrentMonth().sumOf { it.cost }.toString()
    }

    override suspend fun getCostSumPreviousMonth(): String {
        return getCostsPreviousMonth().sumOf { it.cost }.toString()
    }

    override suspend fun getCostsCurrentMonth(): List<Cost> {
        return getCosts().filter { it.month == helper.getCurrentMonth() }
    }

    override suspend fun getCostsPreviousMonth(): List<Cost> {
        return getCosts().filter { it.month == helper.getPrevMonth() }
    }

    override suspend fun getCosts(): List<Cost> {
        return costRepository.getCosts()
    }
}