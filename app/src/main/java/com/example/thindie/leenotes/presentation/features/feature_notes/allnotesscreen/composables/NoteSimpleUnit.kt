package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NoteSimpleUnit(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    backGroundColor: Color,
    onClickNote: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .clickable { onClickNote() }
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
            .background(backGroundColor),

        ) {
        Title(title = title)
        Description(description = description)
    }
}


@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(text = title, style = MaterialTheme.typography.labelLarge)
}


@Composable
private fun Description(
    modifier: Modifier = Modifier,
    description: String,
) {
    Text(text = description, style = MaterialTheme.typography.bodyMedium)
}