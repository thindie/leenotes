package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.domain.NoteManager
import com.example.thindie.leenotes.domain.NotesObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val notesManager: NoteManager,
    private val notesObserver: NotesObserver,
) : ViewModel() {

    private val _notesListState = MutableStateFlow<List<Note>>(emptyList())
    private val _currentNoteState = MutableStateFlow<Note?>(null)
    val notesScreenState = combine(_notesListState, _currentNoteState) { list, note ->
        if (list.isEmpty()) AllNotesScreenState(
            currentNote = null,
            shouldShowDialog = false,
            notesList = list
        )
        else if (note != null) AllNotesScreenState(
            currentNote = note,
            shouldShowDialog = true,
            notesList = list
        )
        else AllNotesScreenState(currentNote = null, shouldShowDialog = false, notesList = list)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = AllNotesScreenState()
    )

    @Inject
    fun onStart() {
        viewModelScope.launch {
            notesObserver
                .observeNotes()
                .onEach {
                    _notesListState.value = it
                }
                .launchIn(this)
        }
    }

    fun onClickedActionButtonForResult(note: Note) {
         viewModelScope.launch {
            notesManager
                .addNote(note)
        }
    }

    fun onConfirmSaveCosts() {

    }

    fun onDismissSaveCosts() {

    }

    fun onSummonRemoveDialog(noteId: Long) {
        viewModelScope.launch {
            _currentNoteState.value =
                notesManager
                    .provideNote(noteId)
        }
     }

    fun onDismissDialog() {
        _currentNoteState.value = null
    }

    data class AllNotesScreenState(
        val currentNote: Note? = null,
        val shouldShowDialog: Boolean = currentNote != null,
        val notesList: List<Note> = emptyList(),
    )
}