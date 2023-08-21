package com.example.thindie.leenotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val costsTable = "tableCosts"
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