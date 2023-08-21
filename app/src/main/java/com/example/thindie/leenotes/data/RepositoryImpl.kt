package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.database.NotesEntity
import com.example.thindie.leenotes.data.mapper.toNote
import com.example.thindie.leenotes.data.mapper.toNotesEntity
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.domain.NotesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class RepositoryImpl @Inject constructor(private val dao: NotesDao) : NotesRepository {
    override suspend fun provideNote(id: Long): Note {
        return getNoteEntity(id).toNote()
    }

    override suspend fun deleteNoteSaveCost(id: Long) {
        val note = getNoteEntity(id)
        dao.deleteNote(note)
    }

    override suspend fun deleteNoteDeleteCost(id: Long) {
        val note = getNoteEntity(id)
        dao.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.upsertNote(note.toNotesEntity())
    }

    override suspend fun addNote(note: Note) {
        updateNote(note)
    }

    override fun observeNotes(): Flow<List<Note>> {
        return dao.observeNotes()
            .map { list ->
                list.map { it.toNote() }
            }
    }

    private suspend fun getNoteEntity(id: Long): NotesEntity {
        return dao.getNote(id)
    }
}