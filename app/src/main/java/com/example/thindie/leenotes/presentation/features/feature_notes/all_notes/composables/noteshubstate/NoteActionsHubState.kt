package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NoteScreenEvent

@Stable
class NoteActionsHubState(val onEvent: (NoteScreenEvent) -> Unit) {

    var isInputVisible by mutableStateOf(false)
        private set
    var inputFieldStringState by mutableStateOf("")
        private set

    var isInputFieldEmpty by mutableStateOf(true)
        private set
    var shouldShowSendAction by mutableStateOf(false)
        private set

    fun onInput(input: String) {
        if (input.isEmpty()) {
            isInputFieldEmpty = true
            shouldShowSendAction = false

        } else {
            isInputFieldEmpty = false
            shouldShowSendAction = true
        }
        inputFieldStringState = input
    }

    fun onClickInput() {
        isInputVisible = !isInputVisible
    }

    fun onClearOutput() {
        onInput("")
    }


    fun onSend() {
        isInputVisible = false
        onEvent(NoteScreenEvent.CreateNote(inputFieldStringState))
        onClearOutput()
    }

    fun onFilter(type: NoteType) {
        onEvent(NoteScreenEvent.Filter(type))
    }
}