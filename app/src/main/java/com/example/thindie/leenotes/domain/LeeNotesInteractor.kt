package com.example.thindie.leenotes.domain

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class LeeNotesInteractor @Inject constructor(private val repository: NotesRepository) :
    NotesObserver, NoteManager {
    override suspend fun provideNote(id: Long): Note {
        return repository.provideNote(id)
    }

    override suspend fun deleteNote(id: Long) {
        repository.deleteNote(id)
    }


    override suspend fun updateNote(note: Note) {
        repository.updateNote(note)
    }

    override suspend fun addNote(note: Note) {
        repository.addNote(note)
    }

    override suspend fun getCurrentNotesCost():  String {
       return repository.getCurrentNotesCost()
    }

    override fun observeNotes(): Flow<List<Note>> {
        return repository.observeNotes()
    }
}