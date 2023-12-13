package com.example.thindie.leenotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.leenotes.data.database.DataBaseContract.notesTable


@Entity(tableName = notesTable)
data class NoteDbModel(
    val title: String,
    val body: String,
    val cost: Int,
    val tagShadow: String,
    val hyperLink: String,
    @PrimaryKey(autoGenerate = false)
    val timestamp: Long,
    val createdAt: String
)