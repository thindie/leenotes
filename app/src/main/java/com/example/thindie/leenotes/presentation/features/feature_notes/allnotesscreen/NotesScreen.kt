package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.composables.NoteActionsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.viewodel.NotesScreenViewModel

@Composable
fun NotesScreen(modifier: Modifier = Modifier, viewModel: NotesScreenViewModel) {

    val screenState by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        screenState.notesList.forEach {
                Text(text = it.title)
            }
        NoteActionsHub(onEvent = viewModel::onNoteScreenEvent)
    }
}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    LeenotesTheme {

    }
}