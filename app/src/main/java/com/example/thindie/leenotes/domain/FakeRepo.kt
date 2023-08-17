package com.example.thindie.leenotes.domain

import android.util.Log
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRepo @Inject constructor() : NoteManager, NotesObserver {

    private val listFlow = MutableStateFlow(emptyList<Note>())
    private val list = mutableListOf<Note>()
    override suspend fun provideNote(id: Long): Note {
        return requireNotNull(list.find { it.timeStamp == id })
    }

    override suspend fun deleteNoteSaveCost(id: Long) {
        list.removeIf { it.timeStamp == id }
        refreshList()
    }

    override suspend fun deleteNoteDeleteCost(id: Long) {
        list.removeIf { it.timeStamp == id }
        refreshList()
    }

    override suspend fun addNote(note: Note) {

        list.add(note)
        refreshList()
    }

    private fun refreshList() {
        listFlow.value = list
    }

    override fun observeNotes(): Flow<List<Note>> {
        return listFlow
    }
}