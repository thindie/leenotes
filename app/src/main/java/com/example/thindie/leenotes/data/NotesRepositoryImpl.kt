package com.example.thindie.leenotes.data

import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.data.database.entities.BindingsDbModel
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import com.example.thindie.leenotes.data.mapper.map
import com.example.thindie.leenotes.data.mapper.toBindingsDbModel
import com.example.thindie.leenotes.data.mapper.toCostDbModel
import com.example.thindie.leenotes.data.mapper.toNoteDbModel
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.entities.NoteBindings
import com.example.thindie.leenotes.domain.mapCollection
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NoteFeatureScope
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@NoteFeatureScope
class NotesRepositoryImpl @Inject constructor(
    private val dao: NotesDao,
    private val bindingsDao: BindingsDao,
    private val costsDao: CostsDao,
) : NotesRepository {
    override suspend fun addNote(initialNoteText: String) {
        NotesBuilder
            .init(initialNoteText)
            .splitInitialString()
            .build()
            .rememberNote(dao = dao, costsDao = costsDao, bindingsDao = bindingsDao)
    }

    override suspend fun updateNote(note: Note) {

    }

    override suspend fun deleteNote(id: Int) {

    }

    override fun observeNotes(): Flow<List<Note>> {
        return dao
            .observeNotes()
            .mapCollection{ dbModel ->  dbModel.mapToNote() }
    }

    private suspend fun getBindingDbModel(id: Int?): BindingsDbModel? {
        return if (id != null) {
            bindingsDao.getBinding(id)
        } else null
    }
    private suspend fun NoteDbModel.mapToNote(): Note{
        return map(::getBindingDbModel, ::getCostDbModel)
    }


    private suspend fun getCostDbModel(id: Int?): CostDbModel? {
        return if (id != null) {
            costsDao.getCost(id)
        } else null
    }



    private suspend fun Note.rememberNote(
        dao: NotesDao,
        costsDao: CostsDao,
        bindingsDao: BindingsDao,
    ) {
        val costId: Int? = addCostOrNull(isSpendable, cost, costsDao)
        val bindingsId: Int? = addBindingsOrNull(hasProperties, bindings, bindingsDao)
        val noteDbModel = toNoteDbModel()
            .copy(costId = costId, bindingsId = bindingsId)
        dao.upsertNote(noteDbModel)
    }

    private suspend fun addCostOrNull(
        condition: Boolean,
        cost: Cost?,
        costsDao: CostsDao,
    ): Int? {
        val costDbModel = if (condition) {
            requireNotNull(cost).toCostDbModel()
        } else null

        return if (costDbModel != null) {
            costsDao
                .addCost(costDbModel)
                .toInt()
        } else null
    }

    private suspend fun addBindingsOrNull(
        condition: Boolean,
        bindings: NoteBindings?,
        bindingsDao: BindingsDao,
    ): Int? {
        val bindingsDbModel = if (condition) {
            requireNotNull(bindings).toBindingsDbModel()
        } else null

        return if (bindingsDbModel != null) {
            bindingsDao
                .addBinding(bindingsDbModel)
                .toInt()
        } else null
    }
}