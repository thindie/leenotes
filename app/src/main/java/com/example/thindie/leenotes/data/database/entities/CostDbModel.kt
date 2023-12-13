package com.example.thindie.leenotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.leenotes.data.database.DataBaseContract.costsTable


@Entity(tableName = costsTable)
data class CostDbModel(
    @PrimaryKey (autoGenerate = false)
    val timeStamp: Long,
    val title: String,
    val day: String,
    val month: String,
    val year: String,
    val cost: Int
)