package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state

import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.domain.entities.Note

data class NotesScreenState (
    val notesList: List<Note> = emptyList(),
    val noteType: NoteType = NoteType.NON_SPECIFIED,
    val afterTimeStampInMillis: Long? = null
    )

fun List<Note>.toState() = NotesScreenState(this)
