package com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.InputFieldState
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.rememberInputFieldState
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteNoteViewModel
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteViewModelEvent

@Composable
fun ConcreteNoteScreen(viewModel: ConcreteNoteViewModel, modifier: Modifier = Modifier) {

    val viewState by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    val colorState by animateColorAsState(
        targetValue = if (viewState.isEditingNow) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceTint
        }, label = ""
    )

    val titleInputFieldState: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_enter_task
    )

    val descriptionInputField: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_summary
    )

    val costInputField: InputFieldState = rememberInputFieldState(
        isNumeric = true, label = R.string.text_field_summary
    )

    val bindingsInputState: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_summary
    )


    Scaffold(
        topBar = {
            NotesTopAppBar {}
        }, containerColor = colorState
    ) {
        Column(modifier = modifier.padding(it)) {
            Title(
                note = viewState.note,
                editingNow = viewState.isEditingNow,
                titleInputState = titleInputFieldState
            )
            Body(
                note = viewState.note,
                editingNow = viewState.isEditingNow,
                description = descriptionInputField,
                cost = costInputField,
                bindings = bindingsInputState
            )
            Controllers(note = viewState.note,
                editingNow = viewState.isEditingNow,
                onClickEdit = {},
                onClickDelete = {},
                onClickSave = {
                    viewModel.onEvent(
                        ConcreteViewModelEvent.Save(
                            title = titleInputFieldState.fieldValue,
                            description = descriptionInputField.fieldValue,
                            cost = costInputField.fieldValue,
                            bindings = bindingsInputState.fieldValue
                        )
                    )
                })
        }
    }
}

@Composable
private fun Title(note: Note?, editingNow: Boolean, titleInputState: InputFieldState) {
    if (note != null) Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
        }

        AnimatedVisibility(visible = editingNow) {
            titleInputState.onValueChange(note.title)
            OutlinedTextField(
                value = titleInputState.fieldValue, onValueChange = titleInputState::onValueChange
            )
        }
    }
}

@Composable
private fun Controllers(
    note: Note?,
    editingNow: Boolean,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickSave: () -> Unit,
) {
    if (note != null) Column {
        Row {

        }
        AnimatedVisibility(visible = editingNow) {

        }
    }
}

@Composable
private fun Body(
    note: Note?,
    editingNow: Boolean,
    description: InputFieldState,
    cost: InputFieldState,
    bindings: InputFieldState,
) {
    if (note != null) Column {
        DescriptionSection(note, editingNow, description)
        CostSection(note, editingNow, cost)
        BindingsSection(note, editingNow, bindings)
    }
}

@Composable
private fun BindingsSection(note: Note, editingNow: Boolean, titleInputState: InputFieldState) {
    if (note.bindings != null) {

    }
    AnimatedVisibility(visible = editingNow) {
        titleInputState.onValueChange(note.bindings?.properties.orEmpty())
        OutlinedTextField(
            value = titleInputState.fieldValue, onValueChange = titleInputState::onValueChange
        )
    }
}

@Composable
private fun CostSection(note: Note, editingNow: Boolean, titleInputState: InputFieldState) {
    if (note.cost != null) {
            Text(text = note.cost.price.toString())
    }
    AnimatedVisibility(visible = editingNow) {
        titleInputState.onValueChange(note.cost?.price.toString())
        OutlinedTextField(
            value = titleInputState.fieldValue, onValueChange = titleInputState::onValueChange
        )
    }
}

@Composable
private fun DescriptionSection(note: Note, editingNow: Boolean, description: InputFieldState) {
    if (note.description.isNotBlank()) {
        Text(text = note.description)
    }
    AnimatedVisibility(visible = editingNow) {

    }
}




