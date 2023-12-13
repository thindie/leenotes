package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.ui.common.searching
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class NotesScreenViewModel @Inject constructor(
    private val notesManager: NoteManager,
    private val notesObserver: NotesObserver,
    private val costManager: CostManager,
) : ViewModel() {

    private val _notesListState = MutableStateFlow<List<Note>>(emptyList())
    private val _currentNoteState = MutableStateFlow<Note?>(null)

    private val _currentSearch = MutableStateFlow("")

    val notesScreenState =
        combine(_notesListState, _currentNoteState, _currentSearch) { list, note, search ->
            if (list.isEmpty()) AllNotesScreenState(
                currentNote = null,
                shouldShowDialog = false,
                notesList = list
            )
            else if (note != null) AllNotesScreenState(
                currentNote = note,
                shouldShowDialog = true,
                notesList = list.searching(search)
            )
            else AllNotesScreenState(
                currentNote = null,
                shouldShowDialog = false,
                notesList = list.searching(search)
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AllNotesScreenState()
        )

    fun onSearchGoing(searchField: State<String>) {
        _currentSearch.value = searchField.value
    }

    @Inject
    fun onStart() {
        viewModelScope.launch(Dispatchers.IO) {
            notesObserver
                .observeNotes()
                .onEach { notes ->
                    _notesListState.value = notes
                }
                .launchIn(this)
        }
    }

    fun onClickedActionButtonForResult(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesManager
                .addNote(note)
        }
    }

    fun onConfirmSaveCosts() {
        viewModelScope.launch(Dispatchers.IO) {
            notesManager.deleteNote(_currentNoteState.value?.timeStamp ?: 0)
            if (_currentNoteState.value?.title.toString()
                    .isNotBlank() && _currentNoteState.value?.isCost == true
            ) {
                costManager.addCost(
                    requireNotNull(_currentNoteState.value)
                )
            }
            onDismissDialog()
        }
    }

    fun onDismissSaveCosts() {
        viewModelScope.launch(Dispatchers.IO) {
            notesManager.deleteNote(_currentNoteState.value?.timeStamp ?: 0)
            onDismissDialog()
        }
    }

    fun onSummonRemoveDialog(noteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
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
