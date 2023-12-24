package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.viewodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.usecase.CreateNoteUseCase
import com.example.thindie.leenotes.domain.usecase.DestroyNoteUseCase
import com.example.thindie.leenotes.domain.usecase.ObserveNotesUseCase
import com.example.thindie.leenotes.domain.usecase.UpdateNoteUseCase
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NoteScreenEvent
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NotesScreenState
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.toState
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NoteFeatureScope
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@NoteFeatureScope
class NotesScreenViewModel @Inject
constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val createNoteUseCase: CreateNoteUseCase,
    observeNotesUseCase: ObserveNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val destroyNoteUseCase: DestroyNoteUseCase,
) : ViewModel() {

    private val selectedNoteType = MutableStateFlow(NoteType.NON_SPECIFIED)
    private val selectedTimeStamp = MutableStateFlow<Long?>(null)


    val screenState = observeNotesUseCase()
        .map(List<Note>::toState)
        .combine(selectedNoteType, ::updateStateWithNoteType)
        .combine(selectedTimeStamp, ::updateStateWithNoteTime)
        .stateIn(
            initialValue = NotesScreenState(),
            started = setSharedStarted(),
            scope = viewModelScope
        )

    private fun onClickCreateNote(rawString: String){
        viewModelScope.launch (ioDispatcher){
            createNoteUseCase(rawString)
        }
    }

    private fun onClickUpdate(note: Note){
        viewModelScope.launch(ioDispatcher) {
            updateNoteUseCase(note)
        }
    }

    private fun onClickDeleteNote(id: Int){
        viewModelScope.launch(ioDispatcher) {
            destroyNoteUseCase(id)
        }
    }

    private fun onClickFilter(type: NoteType){
         selectedNoteType.update { type }
    }

    fun onNoteScreenEvent(event: NoteScreenEvent){
        when (event){
            is NoteScreenEvent.CreateNote -> onClickCreateNote(event.raw)
            is NoteScreenEvent.DeleteNote -> onClickDeleteNote(event.id)
            is NoteScreenEvent.Filter -> onClickFilter(event.type)
            is NoteScreenEvent.UpdateNote -> onClickUpdate(event.note)
        }
    }

    private fun updateStateWithNoteTime(state: NotesScreenState, time: Long?) =
        state.copy(afterTimeStampInMillis = time)

    private fun updateStateWithNoteType(state: NotesScreenState, type: NoteType) : NotesScreenState {
        val list = state.notesList
       return when (type){
            NoteType.COST -> state.copy(notesList = list.filter(Note::isSpendable))
            NoteType.NON_SPECIFIED -> state
            NoteType.HAS_HYPER -> state.copy(notesList = list.filter(Note::hasProperties))
        }
    }


    private fun setSharedStarted() = SharingStarted.WhileSubscribed(5_000L)
}
