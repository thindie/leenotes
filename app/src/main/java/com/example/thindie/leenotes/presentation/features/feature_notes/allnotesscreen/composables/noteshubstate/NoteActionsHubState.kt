package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class NoteActionsHubState(){
    var inputFieldStringState by mutableStateOf("")
        private set

    var isInputFieldEmpty by mutableStateOf(true)

   fun onInput(){}
   fun onClearInput(){}

}