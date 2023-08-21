package com.example.thindie.leenotes.domain

import javax.inject.Inject

class CostsInteractor @Inject constructor(private val costRepository: CostRepository) :
    CostManager {
    override suspend fun addCost(note: Note) {
        costRepository.addCost(note.map())
    }

    override suspend fun getCostSum(): String {
        return costRepository.getCostSum()
    }

    override suspend fun getCosts(): List<Cost> {
        return costRepository.getCosts()
    }
}