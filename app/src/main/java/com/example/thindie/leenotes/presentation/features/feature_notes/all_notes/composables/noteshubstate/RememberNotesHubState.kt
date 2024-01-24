package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.thindie.leenotes.common.design_system.input_field.rememberInputFieldState
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NoteScreenEvent

@Composable
fun rememberNoteActionsHubState(onEvent: (NoteScreenEvent) -> Unit): NoteActionsHubState {
    val inputFieldState = rememberInputFieldState(isNumeric = false, label = 0 ) {}
    return remember{
        NoteActionsHubState(onEvent, inputFieldState)
    }
}