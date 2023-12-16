package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.domain.entities.Note

fun Note.toNoteDbModel(): NoteDbModel {
    return NoteDbModel(title = title, description =  description, creationTimeInMillis =  creationTimeInMillis)
}