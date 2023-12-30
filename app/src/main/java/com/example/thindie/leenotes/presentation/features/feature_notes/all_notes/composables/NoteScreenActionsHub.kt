package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.rememberNoteActionsHubState
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NoteScreenEvent


@Composable
fun NoteActionsHub(
    modifier: Modifier = Modifier,
    onEvent: (NoteScreenEvent) -> Unit,
    notesActionsHubState: NoteActionsHubState = rememberNoteActionsHubState(onEvent),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        NoteCreationUnit(notesActionsHubState)
        Actions(notesActionsHubState)
    }
}

@Composable
fun NoteCreationUnit(notesActionsHubState: NoteActionsHubState) {
    NoteInputField(state = notesActionsHubState)
}
