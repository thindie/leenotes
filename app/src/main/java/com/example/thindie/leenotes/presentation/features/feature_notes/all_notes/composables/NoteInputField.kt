package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState
import kotlinx.coroutines.delay

@Composable
fun NoteInputField(
    modifier: Modifier = Modifier,
    state: NoteActionsHubState,
) {
    val focusRequester = remember {
        FocusRequester()
    }

    var shouldRequestFocus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = shouldRequestFocus, block = {
        if (shouldRequestFocus){
            delay(100L)
            focusRequester.requestFocus()
            shouldRequestFocus = false
        }
    })

    val focusedModifier = Modifier.focusRequester(focusRequester)

    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Label(state) {
            shouldRequestFocus = true
        }
        AnimatedVisibility(visible = state.isInputVisible) {
            BasicTextField(
                modifier = modifier.then(focusedModifier),
                value = state.inputFieldStringState,
                onValueChange = state::onInput,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                cursorBrush = Brush.linearGradient(listOf(getColor(), getColor()))
            )
        }
    }
}


@Composable
private fun Label(state: NoteActionsHubState, notifyClicked: () -> Unit) {
    OutlinedIconButton(onClick = { state.onClickInput(); notifyClicked.invoke() }) {
        Icon(painter = IconHolder.render(IconsHub.create).getIcon(), contentDescription = null)
    }
}
