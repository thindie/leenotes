package com.example.thindie.leenotes.presentation.features.feature_note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.entities.Note
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn

class ConcreteNoteViewModel @Inject constructor() : ViewModel() {

    private val note = MutableStateFlow<Note?>(null)
    private val isEditing = MutableStateFlow(false)

    val screenState = note
        .filterNotNull()
        .combine(isEditing)
        { note, editStatus ->
            ConcreteNoteUiState(note, editStatus)
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ConcreteNoteUiState()
        )

    fun onEvent(event: ConcreteViewModelEvent) {
        when (event) {
            ConcreteViewModelEvent.DeleteCurrent -> TODO()
            ConcreteViewModelEvent.Edit -> TODO()
            is ConcreteViewModelEvent.ID -> {
                onIdEvent(event.id)
            }

            ConcreteViewModelEvent.Save -> TODO()
        }
    }

    private fun onIdEvent(id: Int) {
        if (id != -1) {

        }
    }
}