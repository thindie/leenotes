package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state

import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.domain.entities.Note

sealed class NoteScreenEvent{
    data class DeleteNote(val id: Int): NoteScreenEvent()
    data class CreateNote(val raw: String): NoteScreenEvent()

    data class UpdateNote(val note: Note): NoteScreenEvent()

    data class Filter(val type: NoteType): NoteScreenEvent()
}
