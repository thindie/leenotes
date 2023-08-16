package com.example.thindie.leenotes.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.common.NotesButton
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFieldState
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputField
import com.example.thindie.leenotes.ui.common.inputfields.rememberInputState
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.typo


@Composable
fun TaskDialogContent(
    modifier: Modifier = Modifier,
    headLine: NotesInputFieldState,
    body: NotesInputFieldState,
    onClickPlan: (String, String) -> Unit,
    onClickDismiss: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 9.dp, vertical = 17.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 22.dp),
            style = typo.headlineSmall,
            text = stringResource(id = R.string.text_field_task)
        )
        NotesInputField(state = headLine)
        NotesInputField(state = body)
        NotesButton(title = R.string.button_label_change_mind, isOutlined = true) {
            onClickDismiss()
        }
        NotesButton(title = R.string.button_label_good, isOutlined = false) {
            onClickPlan(headLine.fieldState.value, body.fieldState.value)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskDialogContentPreview() {
    LeenotesTheme {
        NotesDialog(onDismissRequest = {}) {
            TaskDialogContent(
                headLine = rememberInputState(isSingleLine = true),
                body = rememberInputState(isSingleLine = false),
                onClickPlan = { p1, p2 ->

                }
            ) {

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TaskDialogContentPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesDialog(onDismissRequest = {}) {
            TaskDialogContent(
                headLine = rememberInputState(isSingleLine = true),
                body = rememberInputState(isSingleLine = false),
                onClickPlan = { p1, p2 ->

                }
            ) {

            }
        }
    }
}