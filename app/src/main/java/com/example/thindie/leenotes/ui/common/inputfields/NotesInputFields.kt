package com.example.thindie.leenotes.ui.common.inputfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.typo

@Composable
fun NotesInputFields(modifier: Modifier = Modifier, state: NotesInputFieldState) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(state.width.value)
            .height(state.height)
            .padding(start = 8.dp, end = 8.dp, top = 3.dp, bottom = 3.dp),
        value = state.fieldState.value,
        textStyle = typo.bodyLarge,
        colors = state.textFieldColors,
        shape = RoundedCornerShape(15.dp),
        singleLine = state.isSingleLine,
        leadingIcon = { if (state.Icon != null) state.Icon },
        label = { state.fieldHint },
        supportingText = { state.fieldSupportingText },
        onValueChange = state::onValueChange
    )
}


@Preview(showBackground = true)
@Composable
fun NotesInputFieldsPreview() {
    LeenotesTheme(darkTheme = false) {
        NotesInputFields(
            state = rememberInputState(
                isSingleLine = true,
                hint = R.string.text_field_hint_search
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun NotesInputFieldsPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesInputFields(
            state = rememberInputState(
                isSingleLine = true,
                hint = R.string.text_field_hint_search
            )
        )
    }

}