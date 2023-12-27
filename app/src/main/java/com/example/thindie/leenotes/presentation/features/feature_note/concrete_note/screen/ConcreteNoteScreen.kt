package com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteNoteViewModel

@Composable
fun ConcreteNoteScreen(viewModel: ConcreteNoteViewModel, modifier: Modifier = Modifier) {

    val viewState by
    viewModel
        .screenState
        .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    val colorState by
    animateColorAsState(
        targetValue = if (viewState.isEditingNow) {
            MaterialTheme
                .colorScheme
                .primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceTint
        }, label = ""
    )
    Scaffold(
        topBar = {
            NotesTopAppBar {}
        },
        containerColor = colorState
    ) {
        Column(modifier = modifier.padding(it)) {
            Title(viewState.note, viewState.isEditingNow)
            Body(viewState.note, viewState.isEditingNow)
            Controllers(
                viewState.note,
                viewState.isEditingNow,
                onClickEdit = {},
                onClickDelete = {},
                onClickSave = {})
        }
    }
}

@Composable
fun Title(note: Note?, editingNow: Boolean) {
    if (note != null)
        Column {

        }
}

@Composable
fun Controllers(
    note: Note?,
    editingNow: Boolean,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickSave: () -> Unit,
) {
    if (note != null)
        Column {
            AnimatedVisibility(visible = editingNow) {

            }
        }
}

@Composable
fun Body(note: Note?, editingNow: Boolean) {
    if (note != null)
        Column {
            DescriptionSection(note, editingNow)
            CostSection(note, editingNow)
            BindingsSection(note, editingNow)
        }
}

@Composable
fun BindingsSection(note: Note, editingNow: Boolean) {
    if (note.bindings != null) {

    }
    AnimatedVisibility(visible = editingNow) {

    }
}

@Composable
fun CostSection(note: Note, editingNow: Boolean) {
    if (note.cost != null) {

    }
    AnimatedVisibility(visible = editingNow) {

    }
}

@Composable
fun DescriptionSection(note: Note, editingNow: Boolean) {
    if (note.description.isNotBlank()) {

    }
    AnimatedVisibility(visible = editingNow) {

    }
}




