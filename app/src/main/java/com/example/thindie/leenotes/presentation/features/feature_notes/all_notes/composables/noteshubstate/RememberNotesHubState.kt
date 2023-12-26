package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NoteScreenEvent

@Composable
fun rememberNoteActionsHubState(onEvent: (NoteScreenEvent) -> Unit): NoteActionsHubState {
    return remember{
        NoteActionsHubState(onEvent)
    }
}