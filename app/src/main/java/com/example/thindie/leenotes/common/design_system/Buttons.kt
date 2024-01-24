package com.example.thindie.leenotes.common.design_system

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme

@Composable
fun LeeNotesButton(modifier: Modifier = Modifier, onClick: () -> Unit, @StringRes title: Int) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(0.5f)
    ) {
        Text(text = stringResource(id = title))
    }
}
