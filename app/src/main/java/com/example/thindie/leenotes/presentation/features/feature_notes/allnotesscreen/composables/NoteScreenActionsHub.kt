package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate.NoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate.rememberNoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.state.NoteScreenEvent


@Composable
fun NoteActionsHub(
    modifier: Modifier = Modifier,
    onEvent: (NoteScreenEvent) -> Unit,
    notesActionsHubState: NoteActionsHubState = rememberNoteActionsHubState(onEvent),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NoteCreationUnit(notesActionsHubState)
        Spacer(modifier = modifier.padding(horizontal = 8.dp))
        Actions(notesActionsHubState)
    }
}

@Composable
fun NoteCreationUnit(notesActionsHubState: NoteActionsHubState) {
    NoteInputField(state = notesActionsHubState)
}
