package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.mapper.toCost
import com.example.thindie.leenotes.data.mapper.toNoteBindings
import com.example.thindie.leenotes.domain.NoteProvider
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.features.feature_note.di.ConcreteNoteScope
import javax.inject.Inject

@ConcreteNoteScope
class NoteProviderImpl @Inject constructor(
    private val dao: NotesDao,
    private val costsDao: CostsDao,
    private val bindingsDao: BindingsDao,
) : NoteProvider {
    override suspend fun getNoteById(id: Int): Note {
        val noteDbModel = dao.getNote(id)

        val costDbModel = if (noteDbModel.costId != null) costsDao.getCost(noteDbModel.costId)
        else null

        val bindingsDbModel =
            if (noteDbModel.bindingsId != null) bindingsDao.getBinding(noteDbModel.bindingsId)
            else null

        val cost = costDbModel.toCost()
        val binding = bindingsDbModel.toNoteBindings()

        return with(noteDbModel) {
            Note(
                id = id,
                title = title,
                description = description,
                creationTimeInMillis = creationTimeInMillis,
                cost = cost,
                bindings = binding
            )
        }
    }
}