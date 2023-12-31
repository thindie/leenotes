package com.example.thindie.leenotes.presentation.features.feature_note.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.entities.NoteBindings
import com.example.thindie.leenotes.domain.usecase.DestroyNoteUseCase
import com.example.thindie.leenotes.domain.usecase.GetNoteUseCase
import com.example.thindie.leenotes.domain.usecase.UpdateNoteUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private fun String.getOrReject(price: Double): Double {
    return try {
        toInt().toDouble()
    } catch (_: Exception) {
        price
    }
}

class ConcreteNoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DestroyNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
) : ViewModel() {

    private val note = MutableStateFlow<Note?>(null)
    private val isEditing = MutableStateFlow(false)
    private var isSpent by mutableStateOf(false)

    val screenState = note.filterNotNull().combine(isEditing) { note, editStatus ->
        ConcreteNoteUiState(note, editStatus, note.cost?.isBought ?: false)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ConcreteNoteUiState(isSpent = false)
    )


    fun onEvent(event: ConcreteViewModelEvent) {
        when (event) {
            ConcreteViewModelEvent.DeleteCurrent -> {
                val currentNote = note.value
                note.update { null }
                if (currentNote != null) {
                    viewModelScope.launch {
                        deleteNoteUseCase.invoke(currentNote.id)
                    }
                }
            }

            ConcreteViewModelEvent.Edit -> {
                isEditing.getAndUpdate { true }
            }

            is ConcreteViewModelEvent.ID -> {
                onIdEvent(event.id)
            }

            is ConcreteViewModelEvent.Save -> {
                val receivedParams = event
                val currentNote = note.value
                if (currentNote != null) {
                    val title = receivedParams.title
                    val desc = receivedParams.description
                    val cost = receivedParams.cost.tryParseOrDefault()
                    val isBoughtNow = isSpent
                    val bindings = receivedParams.bindings
                    val changedNote = currentNote.copy(
                        id = currentNote.id,
                        title = title,
                        description = desc,
                        creationTimeInMillis = currentNote.creationTimeInMillis,
                        cost = renewCost(currentNote, cost, isBoughtNow),
                        bindings = renewBindings(currentNote, bindings)
                    )
                    isEditing.getAndUpdate { false }
                    viewModelScope.launch(dispatcherIO) { updateNoteUseCase.invoke(changedNote) }
                }
            }

            ConcreteViewModelEvent.NotifySpent -> {
                isSpent = !isSpent
            }
        }
    }

    private fun renewBindings(currentNote: Note, bindings: String): NoteBindings? {
        val currentBindings = currentNote.bindings
        return if (currentBindings == null && bindings.isNotBlank()) {
            NoteBindings(id = 0, properties = NoteBindings.buildProperties(bindings))
        } else {
            currentBindings?.copy(properties = NoteBindings.buildProperties(bindings).ifBlank {
                NoteBindings.buildProperties(
                    currentBindings.properties
                )
            })
        }
    }


    private fun renewCost(currentNote: Note, newCost: Double, boughtNow: Boolean): Cost? {
        val currentCost = currentNote.cost
        return if (currentCost != null) {
            if (currentCost.isBought) currentCost
            else currentCost.copy(id = currentCost.id, price = newCost, isBought = boughtNow)
        } else {
            if (newCost == 0.0) null
            else Cost(price = newCost, isBought = isSpent)
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

private fun String.tryParseOrDefault(): Double {
    return try {
        toDouble()
    } catch (_: Exception) {
        0.0
    }
}


