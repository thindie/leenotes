package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.presentation.IconsHub


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputRow(
    modifier: Modifier = Modifier,
    state: InputFieldState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(value = state.fieldValue,
            onValueChange = state::onValueChange,
            shape = MaterialTheme.shapes.extraLarge,
            singleLine = state.isNumeric,
            keyboardOptions = state.keyboardOptions,
            placeholder = { Text(text = stringResource(id = state.label)) })
        IconButton(onClick = { keyboardController?.hide(); state.onAgreeInputClicked() }) {
            Icon(
                painter = IconHolder.render(IconsHub.confirm).getIcon(),
                contentDescription = null,
                tint = if (state.isInputAgreed.not()) state.colorTypeConfirmed else state.colorNotConfirmed
            )
        }
    }
}