package com.example.thindie.leenotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.leenotes.data.database.DataBaseContract.costsTable


@Entity(tableName = costsTable)
data class CostDbModel(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val price: Double
)