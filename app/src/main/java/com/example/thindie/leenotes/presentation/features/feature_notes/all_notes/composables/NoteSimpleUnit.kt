package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.entities.Note
import kotlin.random.Random


@Composable
fun NoteSimpleUnit(
    modifier: Modifier = Modifier,
    note: Note,
    backGroundColor: Color,
    onClickNote: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClickNote)
            .wrapContentHeight()
            .background(backGroundColor),

        ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title(title = note.title)
            Description(note = note)
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
) {
    val float = Random.nextDouble(from = 0.4, until = 0.9).toFloat()
    Text(
        text = title,
        maxLines = 1,
        style = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onBackground.copy(float),
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .basicMarquee(),
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
private fun Description(
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
        val description: String? = note.description.ifBlank { null }
        description.NoteChip(label = R.string.chip_description)
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