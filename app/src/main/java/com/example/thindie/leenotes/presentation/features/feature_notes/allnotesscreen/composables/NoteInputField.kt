package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.noteshubstate.NoteActionsHubState

@Composable
fun NoteInputField(
    modifier: Modifier = Modifier,
    state: NoteActionsHubState,
) {
    TextField(
        modifier = modifier,
        value = state.inputFieldStringState,
        onValueChange = state::onInput,
        label = { Label() },
        placeholder = { Placeholder() }
    )
}



@Composable
private fun Label() {
    Text(text = stringResource(R.string.text_field_focus_task), style = MaterialTheme.typography.labelSmall)
}

@Composable
private fun Placeholder() {
    Text(text = stringResource(R.string.text_field_enter_task), style = MaterialTheme.typography.labelSmall)
}