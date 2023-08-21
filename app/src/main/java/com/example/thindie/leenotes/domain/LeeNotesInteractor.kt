package com.example.thindie.leenotes.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LeeNotesInteractor @Inject constructor(private val repository: NotesRepository) :
    NotesObserver, NoteManager {
    override suspend fun provideNote(id: Long): Note {
        return repository.provideNote(id)
    }

    override suspend fun deleteNoteSaveCost(id: Long) {
        repository.deleteNoteSaveCost(id)
    }

    override suspend fun deleteNoteDeleteCost(id: Long) {
        repository.deleteNoteDeleteCost(id)
    }

    override suspend fun updateNote(note: Note) {
       repository.updateNote(note)
    }

    override suspend fun addNote(note: Note) {
        repository.addNote(note)
    }

    override fun observeNotes(): Flow<List<Note>> {
        return repository.observeNotes()
    }
}