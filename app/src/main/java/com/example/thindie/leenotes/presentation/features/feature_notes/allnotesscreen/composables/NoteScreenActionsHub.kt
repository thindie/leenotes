package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.leenotes.common.navigation.NavigationEvent
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate.NoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate.rememberNoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NoteScreenEvent


@Composable
fun NoteActionsHub(
    modifier: Modifier = Modifier,
    notesActionsHubState: NoteActionsHubState = rememberNoteActionsHubState(),
    onEvent: (NoteScreenEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        NoteCreationUnit(onEvent, notesActionsHubState)
        Actions(onEvent, notesActionsHubState)
    }
}

@Composable
fun Actions(onEvent: (NoteScreenEvent) -> Unit, notesActionsHubState: NoteActionsHubState) {

}

@Composable
fun NoteCreationUnit(onEvent: (NoteScreenEvent) -> Unit, notesActionsHubState: NoteActionsHubState) {

}
