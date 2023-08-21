package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.mapper.toCost
import com.example.thindie.leenotes.data.mapper.toCostDbModel
import com.example.thindie.leenotes.domain.Cost
import com.example.thindie.leenotes.domain.CostRepository
import javax.inject.Inject


class CostRepositoryImpl @Inject constructor(private val dao: CostsDao) : CostRepository {


    override suspend fun addCost(cost: Cost) {
        dao.addCost(cost.toCostDbModel())
    }

    override suspend fun getCostSum(): String {
        return getCosts().sumOf { it.cost }.toString()
    }

    override suspend fun getCosts(): List<Cost> {
        return dao.getCosts().map { it.toCost() }
    }
}