package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.thindie.leenotes.data.database.DataBaseContract.costsTable
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CostsDao {

    @Query("SELECT * FROM $costsTable  WHERE id =:id")
    suspend fun getCost(id: Int): CostDbModel

    @Upsert
    suspend fun addCost(costDbModel: CostDbModel): Long

    @Query("DELETE FROM $costsTable WHERE id =:id")
    suspend fun deleteCost(id: Int)

    @Query("SELECT * FROM $costsTable ORDER  BY price ASC  ")
    fun getTopCosts(): Flow<List<CostDbModel>>
}