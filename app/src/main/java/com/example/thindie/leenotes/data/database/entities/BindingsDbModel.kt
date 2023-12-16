package com.example.thindie.leenotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.leenotes.data.database.DataBaseContract.bindingsTable
import com.example.thindie.leenotes.data.database.DataBaseContract.costsTable


@Entity(tableName = bindingsTable)
data class BindingsDbModel(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val properties: String
)