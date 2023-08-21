package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CostsDao {

    @Query("SELECT * FROM $costsTable")
    suspend fun getCosts():  List<CostDbModel>

    @Upsert
    suspend fun addCost(costDbModel: CostDbModel)
}