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

   fun onInput(input: String){}
   fun onClearInput(){}

    fun onSend(){}

    fun onFilter(type: NoteType){}
}