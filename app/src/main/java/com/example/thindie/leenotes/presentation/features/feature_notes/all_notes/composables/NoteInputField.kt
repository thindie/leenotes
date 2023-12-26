package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState

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
    )
}


@Composable
private fun Label() {
    Text(text = stringResource(R.string.text_field_enter_task))
}
