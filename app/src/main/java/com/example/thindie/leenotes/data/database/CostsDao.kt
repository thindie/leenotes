package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.thindie.leenotes.data.database.DataBaseContract.costsTable
import com.example.thindie.leenotes.data.database.entities.CostDbModel

@Dao
interface CostsDao {

    @Query("SELECT * FROM $costsTable  WHERE id =:id")
    suspend fun getCost(id: Int): CostDbModel

    @Upsert
    suspend fun addCost(costDbModel: CostDbModel)

    @Query("DELETE FROM $costsTable WHERE id =:id")
    suspend fun deleteCost(id: Int)
}