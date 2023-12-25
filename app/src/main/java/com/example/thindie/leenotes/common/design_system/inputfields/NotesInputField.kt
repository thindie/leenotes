package com.example.thindie.leenotes.common.design_system.inputfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme

@Composable
fun NotesInputField(modifier: Modifier = Modifier, state: NotesInputFieldState) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(state.width.value)
            .height(state.height)
            .padding(start = 8.dp, end = 8.dp, top = 1.dp, bottom = 6.dp),
        value = state.fieldState.value,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = state.textFieldColors,
        shape = if (!state.isSingleLine) RoundedCornerShape(30.dp) else RoundedCornerShape(52.dp),
        singleLine = state.isSingleLine,
        leadingIcon = { if (state.Icon != null) state.Icon },
        label = { state.fieldHint },
        minLines = if (state.isSingleLine) 1 else 10,
        supportingText = { state.fieldSupportingText },
        onValueChange = state::onValueChange
    )
}


@Preview(showBackground = true)
@Composable
fun NotesInputFieldsPreview() {
    LeenotesTheme(darkTheme = false) {
        NotesInputField(
            state = rememberInputState(
                isSingleLine = true, hint = R.string.text_field_hint_search
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun NotesInputFieldsPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesInputField(
            state = rememberInputState(
                isSingleLine = true, hint = R.string.text_field_hint_search
            )
        )
    }

}