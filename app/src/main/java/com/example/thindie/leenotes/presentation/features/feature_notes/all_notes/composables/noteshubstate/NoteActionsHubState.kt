package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.thindie.leenotes.common.design_system.input_field.InputFieldState
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NoteScreenEvent

@Stable
class NoteActionsHubState(val onEvent: (NoteScreenEvent) -> Unit, private val inputFieldState: InputFieldState) {

    var isInputVisible by mutableStateOf(false)
        private set

    val inputFieldStringState by derivedStateOf { inputFieldState.fieldValue }

    var shouldShowSendAction by mutableStateOf(false)
        private set

    fun onInput(input: String) {
        shouldShowSendAction = input.isNotEmpty()
        inputFieldState.onValueChange(input)
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