package com.example.thindie.leenotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.leenotes.data.database.DataBaseContract.notesTable
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.NoteBindings


@Entity(tableName = notesTable)
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val creationTimeInMillis: Long,
    val costId: Int? = null,
    val bindingsId: Int? = null,
)