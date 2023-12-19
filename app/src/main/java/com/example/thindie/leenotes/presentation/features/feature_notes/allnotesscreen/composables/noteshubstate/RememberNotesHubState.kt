package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NoteScreenEvent

@Composable
fun rememberNoteActionsHubState(onEvent: (NoteScreenEvent) -> Unit): NoteActionsHubState {
    return remember{
        NoteActionsHubState(onEvent)
    }
}