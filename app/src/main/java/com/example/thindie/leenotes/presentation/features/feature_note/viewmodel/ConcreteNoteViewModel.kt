package com.example.thindie.leenotes.presentation.features.feature_note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.usecase.GetNoteUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConcreteNoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
) : ViewModel() {

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
            ConcreteViewModelEvent.DeleteCurrent -> {

            }

            ConcreteViewModelEvent.Edit -> {

            }

            is ConcreteViewModelEvent.ID -> {
                onIdEvent(event.id)
            }

            is ConcreteViewModelEvent.Save -> {

            }

        }
    }

    private fun onIdEvent(id: Int) {
        if (id != -1) {
            viewModelScope.launch(dispatcherIO) {
                val noteById = getNoteUseCase(id)
                note.update { noteById }
            }
        }
    }
}