package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.database.entities.BindingsDbModel
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.data.mapper.toBindingsDbModel
import com.example.thindie.leenotes.data.mapper.toCostDbModel
import com.example.thindie.leenotes.data.mapper.toNoteDbModel
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.entities.NoteBindings
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@HandleScope
class HandleIntentNotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val bindingsDao: BindingsDao,
    private val costsDao: CostsDao,
) : NotesRepository {
    override suspend fun addNote(initialNoteText: String) {}
    override suspend fun updateNote(note: Note) {
        val noteDbModel: NoteDbModel = note.toNoteDbModel()
        val costId = getCostId(note.cost)
        val bindingsId = getBindingsId(note.bindings)
        notesDao.upsertNote(noteDbModel.copy(costId = costId, bindingsId = bindingsId))
    }

    private suspend fun getCostId(cost: Cost?): Int? {
        return if (cost != null) costsDao.addCost(
            CostDbModel(
                price = cost.price,
                isBought = cost.isBought
            )
        ).toInt() else null
    }

    private suspend fun getBindingsId(noteBindings: NoteBindings?): Int? {
        return if (noteBindings != null) bindingsDao.addBinding(
            BindingsDbModel(
                properties = noteBindings.properties
            )
        ).toInt() else null
    }

    override fun observeNotes(): Flow<List<Note>> {
        return flow { }
    }
}