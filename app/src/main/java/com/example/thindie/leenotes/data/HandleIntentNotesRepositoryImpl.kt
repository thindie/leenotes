package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
@HandleScope
class HandleIntentNotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val bindingsDao: BindingsDao,
    private val costsDao: CostsDao,
): NotesRepository {
    override suspend fun addNote(initialNoteText: String) {
     }

    override suspend fun updateNote(note: Note) {

    }

    override suspend fun deleteNote(id: Int) {
    }

    override fun observeNotes(): Flow<List<Note>> {
       return flow {  }
    }
}