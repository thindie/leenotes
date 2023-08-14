package com.example.thindie.leenotes.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo

@Composable
fun NotesButton(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isOutlined: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 9.dp, end = 9.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOutlined) colors.tertiary else colors.primaryContainer,
            contentColor = if (isOutlined) colors.onTertiary else Color.White,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(52.dp)
    ) {
        Text(text = stringResource(id = title), style = typo.bodyLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun NotesButtonPreview() {
    LeenotesTheme {
        Column {
            NotesButton(title = R.string.button_label_oh_no, isOutlined = false) {}
            NotesButton(title = R.string.button_label_oh_no, isOutlined = true) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesButtonPreviewDark() {
    Column {
        NotesButton(title = R.string.button_label_oh_no, isOutlined = false) {}
        NotesButton(title = R.string.button_label_oh_no, isOutlined = true) {}
    }
}