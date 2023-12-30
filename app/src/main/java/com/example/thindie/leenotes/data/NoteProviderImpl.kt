package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.data.mapper.toBindingsDbModel
import com.example.thindie.leenotes.data.mapper.toCost
import com.example.thindie.leenotes.data.mapper.toCostDbModel
import com.example.thindie.leenotes.data.mapper.toNoteBindings
import com.example.thindie.leenotes.data.mapper.toNoteDbModel
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
    override suspend fun getNoteById(id: Int): Note? {
        val noteDbModel = getDbModel(id)

        noteDbModel?.let {
            val costDbModel = if (noteDbModel.costId != null) {
                costsDao.getCost(noteDbModel.costId)
            } else null

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
        return null
    }

    override suspend fun deleteNote(id: Int) {
        val dbModel = getDbModel(id)
        dbModel?.let {
            dao.deleteNote(dbModel.id)
            dbModel.costId?.let { costsDao.deleteCost(it) }
            dbModel.bindingsId?.let { bindingsDao.deleteBinding(it) }
        }

    }

    override suspend fun updateNote(note: Note) {


        val bindings = note.bindings
        val costs = note.cost

        var buildNoteDbModel = note.toNoteDbModel()

        costs?.let {
            buildNoteDbModel = if (it.price > 0.0) {
                val costId =
                    if (it.id > 0) {
                        costsDao.addCost(it.toCostDbModel())
                        it.id
                    } else {
                        costsDao.addCost(it.toCostDbModel()).toInt()
                    }
                buildNoteDbModel.copy(costId = costId)
            } else {
                costsDao.deleteCost(it.id)
                buildNoteDbModel.copy(costId = null)
            }
        }


        bindings?.let {

            val bindingsId =
                if (it.id > 0) {
                    bindingsDao.addBinding(it.toBindingsDbModel())
                    it.id
                } else {
                    bindingsDao.addBinding(it.toBindingsDbModel()).toInt()
                }
            buildNoteDbModel = buildNoteDbModel.copy(bindingsId = bindingsId)

        }



        dao.upsertNote(buildNoteDbModel)

    }

    private suspend fun getDbModel(id: Int): NoteDbModel? {

        return dao.getNote(id)
    }

}
