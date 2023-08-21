package com.example.thindie.leenotes.domain

import kotlinx.coroutines.flow.Flow

interface NotesObserver {
    fun observeNotes(): Flow<List<Note>>
}