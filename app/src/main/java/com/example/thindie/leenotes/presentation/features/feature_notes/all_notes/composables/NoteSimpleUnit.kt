package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.entities.Note


@Composable
fun NoteSimpleUnit(
    modifier: Modifier = Modifier,
    note: Note,
    onClickNote: () -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title(title = note.title, onClickNote = onClickNote)
            Properties(note = note)
        }
        Description(description = note.description)
    }


}

@Composable
private fun Description(modifier: Modifier = Modifier, description: String) {
    Text(
        text = description,
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.onBackground.copy(0.3f),
        ),
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth(0.4f)
            .padding(horizontal = 24.dp),
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
    onClickNote: () -> Unit,
) {

    ClickableText(
        text = AnnotatedString(text = title),
        maxLines = 1,
        style = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        softWrap = true,
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 12.dp),

        onClick = { onClickNote() }
    )
}


@Composable
private fun Properties(
    modifier: Modifier = Modifier,
    note: Note,
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        note.bindings.NoteChip(label = R.string.chip_additions)
        if (note.cost?.isBought != true) {
            note.cost.NoteChip(label = R.string.chip_costs)
        }
        if (note.cost?.isBought == true) {
            note.cost.isBought.NoteChip(label = R.string.chip_spent)
        }
    }
}

@Composable
private fun Any?.NoteChip(@StringRes label: Int) {
    if (this != null) {
        AssistChip(
            modifier = Modifier.scale(0.6f),
            onClick = { },
            label = { Text(text = stringResource(id = label)) },
            enabled = false,
            border = AssistChipDefaults.assistChipBorder()
        )
    }
}