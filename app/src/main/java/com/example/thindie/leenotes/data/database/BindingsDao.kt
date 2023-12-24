package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.thindie.leenotes.data.database.DataBaseContract.bindingsTable
import com.example.thindie.leenotes.data.database.entities.BindingsDbModel

@Dao
interface BindingsDao {
    @Query("SELECT * FROM $bindingsTable  WHERE id =:id")
    suspend fun getBinding(id: Int): BindingsDbModel

    @Upsert
    suspend fun addBinding(bindingsDbModel: BindingsDbModel): Long

    @Query("DELETE FROM $bindingsTable WHERE id =:id")
    suspend fun deleteBinding(id: Int)
}