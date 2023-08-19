package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.domain.NotesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class RepositoryImpl @Inject constructor() : NotesRepository {
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