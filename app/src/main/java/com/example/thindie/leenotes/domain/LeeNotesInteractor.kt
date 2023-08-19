package com.example.thindie.leenotes.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LeeNotesInteractor @Inject constructor(private val repository: NotesRepository) :
    NotesObserver, NoteManager {
    override suspend fun provideNote(id: Long): Note {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteSaveCost(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteDeleteCost(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun observeNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }
}