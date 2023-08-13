package com.example.thindie.leenotes.ui.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme

@Composable
fun NotesButton(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isOutlined: Boolean,
) {

}

@Preview(showBackground = true)
@Composable
fun NotesButtonPreview() {
    LeenotesTheme {
        NotesButton(title = R.string.button_label_oh_no, isOutlined = false)
    }
}