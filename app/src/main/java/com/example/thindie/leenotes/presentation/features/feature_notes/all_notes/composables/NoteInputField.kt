package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState

@Composable
fun NoteInputField(
    modifier: Modifier = Modifier,
    state: NoteActionsHubState,
) {
    Row(
        modifier = modifier.fillMaxWidth(0.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Label()
        BasicTextField(
            modifier = modifier,
            value = state.inputFieldStringState,
            onValueChange = state::onInput,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            cursorBrush = Brush.linearGradient(listOf(getColor(), getColor()))
        )
    }

}


@Composable
private fun Label() {
    Icon(painter = IconHolder.render(IconsHub.create).getIcon(), contentDescription = "")
}
