package com.example.thindie.leenotes.domain

import com.example.thindie.leenotes.domain.entities.Note

interface NoteProvider {
    suspend fun getNoteById(id: Int): Note
}