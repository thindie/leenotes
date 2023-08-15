package com.example.thindie.leenotes.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.common.NotesButton
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo


@Composable
fun RemoveNoteDialogContent(
    modifier: Modifier = Modifier,
    representValue: String,
    onClickConfirm: () -> Unit,
    onClickDismiss: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 9.dp, vertical = 17.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = typo.displaySmall,
            color = colors.onPrimary,
            text = stringResource(id = R.string.text_field_remove)
        )
        Text(
            style = typo.bodyLarge,
            color = colors.onSecondary,
            text = representValue
        )
        NotesButton(title = R.string.button_label_change_mind, isOutlined = true) {
            onClickDismiss()
        }
        NotesButton(title = R.string.button_label_good, isOutlined = false) {
            onClickConfirm()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RemoveNoteDialogContentPreview() {
    LeenotesTheme {
        NotesDialog(onDismissRequest = {}) {
            RemoveNoteDialogContent(representValue = "Value?", onClickConfirm = { }) {

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RemoveNoteDialogContentPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesDialog(onDismissRequest = {}) {
            RemoveNoteDialogContent(representValue = "Value?", onClickConfirm = { }) {

            }
        }
    }
}