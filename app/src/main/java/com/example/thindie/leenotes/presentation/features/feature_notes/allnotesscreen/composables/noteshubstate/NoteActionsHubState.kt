package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NoteScreenEvent

@Stable
class NoteActionsHubState(val onEvent: (NoteScreenEvent) -> Unit) {
    var inputFieldStringState by mutableStateOf("")
        private set

    var isInputFieldEmpty by mutableStateOf(true)
        private set
    var shouldShowSendAction by mutableStateOf(false)
        private set

    fun onInput(input: String) {
        if (input.isEmpty()){
            isInputFieldEmpty = true
            shouldShowSendAction = false

        }
        else{
            isInputFieldEmpty = false
            shouldShowSendAction = true
        }
        inputFieldStringState = input
    }

    fun onClearOutput(){
        onInput("")
    }


    fun onSend(){
        onEvent(NoteScreenEvent.CreateNote(inputFieldStringState ))
        onClearOutput()
    }

    fun onFilter(type: NoteType){
        onEvent(NoteScreenEvent.Filter(type))
    }
}