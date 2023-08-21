package com.example.thindie.leenotes.domain

interface NoteManager {
   suspend fun provideNote(id: Long): Note
   suspend fun deleteNote(id: Long)

    suspend fun updateNote(note: Note)
   suspend fun addNote(note: Note)

   suspend fun getCurrentNotesCost():  String
}