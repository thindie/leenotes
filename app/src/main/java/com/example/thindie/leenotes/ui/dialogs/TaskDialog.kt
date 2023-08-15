package com.example.thindie.leenotes.ui.dialogs

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors

@Composable
fun NotesDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest(false) }) {

        Surface(
            modifier = modifier.size(350.dp),
            shape = RoundedCornerShape(54.dp),
            color = colors.primary
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesDialogPreview() {
    LeenotesTheme {
        NotesDialog(onDismissRequest = {}) {
            Text(text = "DIALOG")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesDialogPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesDialog(onDismissRequest = {}) {
            Text(text = "DIALOG")
        }
    }
}