package com.example.thindie.leenotes.ui.screens.allnotesscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.FakeRepo
import com.example.thindie.leenotes.domain.Note
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
class NotesScreenViewModel @Inject constructor(private val repo: FakeRepo) : ViewModel() {

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
            repo
                .observeNotes()
                .onEach {
                    Log.d("SERVICE_TAG_OBSERVER", it.size.toString())
                    _notesListState.value = it }
                .launchIn(this)
        }
    }

    fun onClickedActionButtonForResult(note: Note) {

        viewModelScope.launch {
            repo
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
            repo
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