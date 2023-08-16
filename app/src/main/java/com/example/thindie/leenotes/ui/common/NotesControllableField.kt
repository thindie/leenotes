package com.example.thindie.leenotes.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputField
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFieldState
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo

@Composable
fun <T : NotesInputFieldState> NotesControllableField(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    state: T,
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NotesInputField(
            modifier = Modifier
                .onFocusChanged {
                    state.onFocusChanged(it)
                }, state = state
        )
        if (state.width.value == state.halvedSizeField) {
            Text(
                modifier = modifier
                    .padding(vertical = 12.dp)
                    .clickable {
                        focusManager.clearFocus();
                        state.onWidthRestore()
                    },
                text = stringResource(id = title),
                style = typo.titleLarge,
                color = colors.primaryContainer
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesSearchFieldPreview() {
    LeenotesTheme {

    }
}

@Preview(showBackground = true)
@Composable
fun NotesSearchFieldPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}