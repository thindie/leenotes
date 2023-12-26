package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.NoteActionsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.NoteSimpleUnit
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NotesScreenViewModel

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesScreenViewModel,
    onSelectArgument: (String) -> Unit,
) {

    val screenState by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    Scaffold(modifier = modifier.imePadding(), bottomBar = {
        NoteActionsHub(onEvent = viewModel::onNoteScreenEvent)
    }) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(screenState.notesList, key = Note::id) {note->
                NoteSimpleUnit(note = note,
                    backGroundColor = getColor(),
                    onClickNote = { onSelectArgument(note.id.toString()) })
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    LeenotesTheme {

    }
}