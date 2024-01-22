package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.NoteComposable
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.common.design_system.input_field.InputFieldState
import com.example.thindie.leenotes.common.design_system.theme.LocalShape


@Composable
fun InputRow(
    modifier: Modifier = Modifier,
    state: InputFieldState,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NoteComposable(
            elevation = 5.dp,
            contentPadding = PaddingValues(0.dp)
        ) {
            TextField(value = state.fieldValue,
                modifier = it,
                onValueChange = state::onValueChange,
                singleLine = state.isNumeric,
                keyboardOptions = state.keyboardOptions,
                placeholder = { Text(text = stringResource(id = state.label)) })
        }
    }
}