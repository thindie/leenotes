package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberNoteActionsHubState(): NoteActionsHubState {
    return remember{
        NoteActionsHubState()
    }
}