package com.example.thindie.leenotes.presentation.features.feature_note.viewmodel

import com.example.thindie.leenotes.domain.entities.Note

data class ConcreteNoteUiState(
    val note: Note? = null,
    val noteTime: String = "",
    val isEditingNow: Boolean = false,
    val isSpent: Boolean,
)