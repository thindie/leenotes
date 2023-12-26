package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.IconsHub


@Composable
fun NoteSimpleUnit(
    modifier: Modifier = Modifier,
    note: Note,
    backGroundColor: Color,
    onClickNote: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .clickable(onClick = onClickNote)
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
            .background(backGroundColor),

        ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title(title = note.title)
            Description(note = note)
        }

    }
}


@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 12.dp)
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
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val description: String? = note.description.ifBlank { null }
        description.RenderIcon(iconHolder = IconHolder.render(IconsHub.settings))
        note.bindings.RenderIcon(iconHolder = IconHolder.render(IconsHub.web))
        note.cost.RenderIcon(iconHolder = IconHolder.render(IconsHub.costs))
    }
}

@Composable
private fun Any?.RenderIcon(iconHolder: IconHolder) {
    if (this != null) {
        Icon(
            painter = iconHolder.getIcon(),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )
    }
}