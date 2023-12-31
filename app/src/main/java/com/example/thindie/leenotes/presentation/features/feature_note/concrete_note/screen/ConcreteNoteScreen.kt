package com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.InputFieldState
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.rememberInputFieldState
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteNoteViewModel
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteViewModelEvent

@Composable
fun ConcreteNoteScreen(
    viewModel: ConcreteNoteViewModel,
    modifier: Modifier = Modifier,
    onReturnStartScreen: () -> Unit,
) {
    val onFieldChange = { viewModel.onEvent(ConcreteViewModelEvent.Edit) }

    val viewState by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)


    val titleInputFieldState: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_enter_task, onFieldChange = onFieldChange
    )

    val descriptionInputField: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_summary, onFieldChange = onFieldChange
    )

    val costInputField: InputFieldState = rememberInputFieldState(
        isNumeric = true, label = R.string.text_field_summary, onFieldChange = onFieldChange
    )

    val bindingsInputState: InputFieldState = rememberInputFieldState(
        isNumeric = false, label = R.string.text_field_summary, onFieldChange = onFieldChange
    )


    Scaffold(
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                note = viewState.note, titleInputState = titleInputFieldState
            )
            Body(
                note = viewState.note,
                editingNow = viewState.isEditingNow,
                description = descriptionInputField,
                cost = costInputField,
                bindings = bindingsInputState,
                onNotifySpent = { viewModel.onEvent(ConcreteViewModelEvent.NotifySpent) }
            )
            Controllers(note = viewState.note,
                editingNow = viewState.isEditingNow,
                onClickDelete = {
                    onReturnStartScreen()
                    viewModel.onEvent(ConcreteViewModelEvent.DeleteCurrent)
                },
                isCostSpent = viewState.isSpent,
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
private fun Title(note: Note?, titleInputState: InputFieldState) {
    if (note != null) Column {
        Row(
            modifier = Modifier
                .background(getColor())
                .clip(MaterialTheme.shapes.extraLarge)
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            titleInputState.onInit(note.title)
            BasicTextField(
                modifier = Modifier.fillMaxWidth(0.7f),
                value = titleInputState.fieldValue,
                onValueChange = titleInputState::onValueChange,
                textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W200),
                cursorBrush = Brush.linearGradient(listOf(getColor(), getColor()))
            )
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedIconButton(
                onClick = { titleInputState.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null
                )
            }
        }

    }
}

@Composable
private fun Controllers(
    note: Note?,
    editingNow: Boolean,
    isCostSpent: Boolean,
    onClickDelete: () -> Unit,
    onClickSave: () -> Unit,
) {

    if (isCostSpent.not()) {
        if (note != null) Column {
            var shouldShowDeleteConfirmation by remember {
                mutableStateOf(false)
            }
            AnimatedVisibility(
                visible = shouldShowDeleteConfirmation, modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(getColor())
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f, true))
                    OutlinedIconButton(onClick = { onClickDelete() }) {
                        Icon(
                            painter = IconHolder.render(IconsHub.confirm).getIcon(),
                            contentDescription = null
                        )
                    }
                    OutlinedIconButton(onClick = { shouldShowDeleteConfirmation = false }) {
                        Icon(
                            painter = IconHolder.render(IconsHub.cancel).getIcon(),
                            contentDescription = null
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Save(onClickSave, editingNow)
                Spacer(modifier = Modifier.weight(1f, true))
                Delete { shouldShowDeleteConfirmation = !shouldShowDeleteConfirmation }
            }

        }
    } else {
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Save(onClickSave = onClickSave, editingNow = editingNow)
            AnimatedVisibility(visible = editingNow.not()) {
                Text(text = stringResource(id = R.string.text_note_cant_be_deleted))
            }

        }
    }
}

@Composable
fun Save(onClickSave: () -> Unit, editingNow: Boolean) {
    AnimatedVisibility(visible = editingNow) {
        OutlinedIconButton(onClick = onClickSave) {
            Icon(painter = IconHolder.render(IconsHub.save).getIcon(), contentDescription = null)
        }
    }

}

@Composable
fun Delete(onClickDelete: () -> Unit) {
    OutlinedIconButton(onClick = onClickDelete) {
        Icon(painter = IconHolder.render(IconsHub.delete).getIcon(), contentDescription = null)
    }
}


@Composable
private fun Body(
    note: Note?,
    editingNow: Boolean,
    description: InputFieldState,
    cost: InputFieldState,
    bindings: InputFieldState,
    onNotifySpent: () -> Unit,
) {
    if (note != null) LazyColumn(
        modifier = Modifier
            .imePadding()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            DescriptionSection(note, description)
        }
        item {
            BindingsSection(note, bindings)
        }
        item {
            CostSection(note, cost, onNotifySpent)
        }
    }
}

@Composable
private fun BindingsSection(note: Note, titleInputState: InputFieldState) {

    val context = LocalContext.current

    fun viewLink(context: Context, url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    }

    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.text_field_additional_info))
            OutlinedIconButton(
                onClick = { titleInputState.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .background(getColor())
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            titleInputState.onInit(note.bindings?.properties.orEmpty())
            BasicTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth(0.7f),
                value = titleInputState.fieldValue,
                onValueChange = titleInputState::onValueChange
            )
            AnimatedVisibility(visible = titleInputState.canBeUseAsLink) {
                OutlinedIconButton(
                    onClick = {
                        viewLink(
                            context = context,
                            url = titleInputState.fieldValue
                        )
                    },
                    modifier = Modifier.scale(0.5f)
                ) {
                    Icon(
                        painter = IconHolder.render(IconsHub.web).getIcon(),
                        contentDescription = null
                    )
                }
            }

        }
    }

}

@Composable
private fun CostSection(note: Note, titleInputState: InputFieldState, onNotifySpent: () -> Unit) {

    var isSpendAtLeast by remember {
        mutableStateOf(note.cost?.isBought ?: false)
    }

    Column(modifier = Modifier.padding(vertical = 10.dp)) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.text_field_cost))
            OutlinedIconButton(
                onClick = { titleInputState.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .background(getColor())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            titleInputState.onInit(note.cost?.price.toString())
            BasicTextField(
                value = titleInputState.fieldValue,
                onValueChange = titleInputState::onValueChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W700),
                keyboardOptions = titleInputState.keyboardOptions
            )
        }
        if (note.cost?.isBought == false) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(
                    onClick = {
                        isSpendAtLeast = !isSpendAtLeast
                        onNotifySpent()
                        titleInputState.onValueChange(titleInputState.fieldValue)
                    },
                    label = { Text(text = stringResource(id = R.string.chip_notify_spend)) },
                    leadingIcon = {
                        if (isSpendAtLeast) Icon(
                            painter = IconHolder.render(IconsHub.confirm).getIcon(),
                            contentDescription = null
                        )
                    })
            }
        }
    }
}


@Composable
private fun DescriptionSection(note: Note, description: InputFieldState) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.text_field_description))
            OutlinedIconButton(
                onClick = { description.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .background(getColor())
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            description.onInit(note.description)
            BasicTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                value = description.fieldValue,
                onValueChange = description::onValueChange
            )
        }
    }
}




