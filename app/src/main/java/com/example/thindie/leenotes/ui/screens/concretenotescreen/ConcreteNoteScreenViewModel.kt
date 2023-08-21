package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.domain.NoteManager
import com.example.thindie.leenotes.domain.NotesObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ConcreteNoteScreenViewModel @Inject constructor(private val notesManager: NoteManager) :
    ViewModel() {

    private val _concreteNoteScreenState = MutableStateFlow<Note?>(null)
    val concreteNoteScreenState = _concreteNoteScreenState.asStateFlow()
    fun onClickDetail(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _concreteNoteScreenState.value = notesManager.provideNote(id)
        }
    }

    fun onConfirmUpdateNote(newNote: Note?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (newNote != null) {
                notesManager.updateNote(newNote)
            }
        }
    }
}