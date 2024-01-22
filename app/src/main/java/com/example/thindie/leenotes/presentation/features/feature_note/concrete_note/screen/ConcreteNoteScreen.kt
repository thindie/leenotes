package com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.WrabbyteInteractiveElement
import com.example.thindie.leenotes.common.design_system.WrabbyteTimeSection
import com.example.thindie.leenotes.common.design_system.coreElementShape
import com.example.thindie.leenotes.common.design_system.firstElementShape
import com.example.thindie.leenotes.common.design_system.input_field.InputFieldState
import com.example.thindie.leenotes.common.design_system.input_field.rememberInputFieldState
import com.example.thindie.leenotes.common.design_system.lastElementShape
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.IconsHub
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


    Scaffold(modifier = modifier, bottomBar = {
        Controllers(note = viewState.note, editingNow = viewState.isEditingNow, onClickDelete = {
            onReturnStartScreen()
            viewModel.onEvent(ConcreteViewModelEvent.DeleteCurrent)
        }, isCostSpent = viewState.isSpent, onClickSave = {
            viewModel.onEvent(
                ConcreteViewModelEvent.Save(
                    title = titleInputFieldState.fieldValue,
                    description = descriptionInputField.fieldValue,
                    cost = costInputField.fieldValue,
                    bindings = bindingsInputState.fieldValue
                )
            )
        })
    }) {
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .padding(it), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title(
                note = viewState.note,
                noteCreationTime = viewState.noteTime,
                titleInputState = titleInputFieldState
            )
            Body(note = viewState.note,
                editingNow = viewState.isEditingNow,
                description = descriptionInputField,
                cost = costInputField,
                bindings = bindingsInputState,
                onNotifySpent = { viewModel.onEvent(ConcreteViewModelEvent.NotifySpent) })
        }
    }


}

@Composable
private fun Title(
    note: Note?,
    titleInputState: InputFieldState,
    noteCreationTime: String,
    shape: Shape = firstElementShape,
) {
    if (note != null) Column {
        WrabbyteTimeSection(currentTime = noteCreationTime)
        WrabbyteInteractiveElement(
            inputState = titleInputState,
            title = R.string.text_label_title,
            initialValue = note.title,
            shape = shape,
            textStyle = MaterialTheme.typography.headlineSmall
        )
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


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Save(
                    onClickSave = onClickSave,
                    editingNow = editingNow
                )
                Delete(isVisible = editingNow.not(), onClickDelete = {
                    shouldShowDeleteConfirmation = !shouldShowDeleteConfirmation
                })

                DeleteConfirmation(
                    shouldShowDeleteConfirmation = shouldShowDeleteConfirmation && editingNow.not(),
                    onClickDelete = onClickDelete
                )
            }

        }
    } else {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Save(onClickSave = onClickSave, editingNow = editingNow)
            AnimatedVisibility(
                visible = editingNow.not(), modifier = Modifier.wrapContentHeight()
            ) {
                OutlinedCard(
                    shape = MaterialTheme.shapes.extraLarge, modifier = Modifier.wrapContentHeight()
                ) {
                    Text(
                        text = stringResource(id = R.string.text_note_cant_be_deleted),
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

            }

        }
    }
}

@Composable
fun Save(onClickSave: () -> Unit, editingNow: Boolean) {
    AnimatedVisibility(visible = editingNow) {
        AssistChip(
            onClick = onClickSave,
            label = { Text(text = stringResource(id = R.string.text_field_save_changes)) },
            leadingIcon = {
                Icon(
                    painter = IconHolder.render(IconsHub.save).getIcon(),
                    contentDescription = null
                )
            })
    }

}

@Composable
fun Delete(onClickDelete: () -> Unit, isVisible: Boolean) {
    AnimatedVisibility(visible = isVisible) {
        OutlinedIconButton(onClick = onClickDelete) {
            Icon(painter = IconHolder.render(IconsHub.delete).getIcon(), contentDescription = null)
        }
    }
}

@Composable
fun DeleteConfirmation(
    shouldShowDeleteConfirmation: Boolean,
    onClickDelete: () -> Unit,
) {
    AnimatedVisibility(
        visible = shouldShowDeleteConfirmation,
        modifier = Modifier
            .wrapContentWidth()
    ) {
        AssistChip(onClick = onClickDelete,
            shape = MaterialTheme.shapes.extraLarge,
            label = {
                Text(text = stringResource(id = R.string.text_field_remove))
            },
            leadingIcon = {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null
                )
            })
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
            .fillMaxWidth()
            .imePadding()
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            DescriptionSection(note = note, description = description)
        }
        item {
            BindingsSection(note = note, titleInputState = bindings)
        }
        item {
            CostSection(note = note, titleInputState = cost, onNotifySpent = onNotifySpent)
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
private fun BindingsSection(
    note: Note,
    titleInputState: InputFieldState,
    shape: Shape = coreElementShape,
) {

    val context = LocalContext.current

    fun viewLink(context: Context, url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    }

    val bindings = if (note.bindings == null) "" else note.bindings.properties

    WrabbyteInteractiveElement(
        inputState = titleInputState,
        title = R.string.text_field_additional_info,
        initialValue = bindings,
        shape = shape,
        textStyle = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Thin
        )
    )


    AnimatedVisibility(
        visible = titleInputState.canBeUseAsLink,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(all = 12.dp)) {
            AssistChip(onClick = {
                viewLink(
                    context = context, url = titleInputState.fieldValue
                )
            }, label = {
                Text(text = stringResource(R.string.button_label_surf))
            }, leadingIcon = {
                Icon(
                    painter = IconHolder.render(IconsHub.web).getIcon(),
                    contentDescription = null
                )
            }, shape = MaterialTheme.shapes.extraLarge
            )
        }
    }
}

@Composable
private fun CostSection(
    note: Note,
    titleInputState: InputFieldState,
    shape: Shape = lastElementShape,
    onNotifySpent: () -> Unit,
) {

    var isSpendAtLeast by remember {
        mutableStateOf(note.cost?.isBought ?: false)
    }


    val price = if (note.cost == null) "" else note.cost.price.toString()

    WrabbyteInteractiveElement(
        inputState = titleInputState,
        initialValue = price,
        shape = shape,
        title = R.string.text_field_cost,
        textStyle = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface
        )
    )



    if (note.cost?.isBought == false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.Start,
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
                },
                shape = MaterialTheme.shapes.extraLarge
            )
        }
    }
}


@Composable
private fun DescriptionSection(
    note: Note,
    description: InputFieldState,
    shape: Shape = coreElementShape,
) {
    WrabbyteInteractiveElement(
        inputState = description,
        initialValue = note.description,
        title = R.string.text_field_description,
        shape = shape,
        textStyle = MaterialTheme.typography.headlineSmall.copy(fontSize = 28.sp)
    )
}


