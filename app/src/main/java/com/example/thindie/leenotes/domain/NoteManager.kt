package com.example.thindie.leenotes.domain

interface NoteManager {
   suspend fun provideNote(id: Long): Note
   suspend fun deleteNoteSaveCost(id: Long)
   suspend fun deleteNoteDeleteCost(id: Long)
    suspend fun updateNote(note: Note)
   suspend fun addNote(note: Note)
}