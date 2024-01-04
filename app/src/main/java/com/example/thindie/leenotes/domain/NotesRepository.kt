package com.example.thindie.leenotes.domain

import com.example.thindie.leenotes.domain.Reasons.MULTI_TARGETED_INITIAL_NOTE_STRING
import com.example.thindie.leenotes.domain.entities.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    @Attention(reason = MULTI_TARGETED_INITIAL_NOTE_STRING)
    suspend fun addNote(initialNoteText: String)
    suspend fun updateNote(note: Note)


    fun observeNotes(): Flow<List<Note>>
}