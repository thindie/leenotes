package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.LeeNotesButton
import com.example.thindie.leenotes.common.design_system.WrabbyteInteractiveElement
import com.example.thindie.leenotes.common.design_system.WrabbyteTimeSection
import com.example.thindie.leenotes.common.design_system.firstElementShape
import com.example.thindie.leenotes.common.design_system.input_field.InputFieldState
import com.example.thindie.leenotes.common.design_system.input_field.rememberInputFieldState
import com.example.thindie.leenotes.common.design_system.lastElementShape
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModelEvent

@Composable
fun HandleIntentScreen(
    modifier: Modifier = Modifier,
    viewModel: HandleShareViewModel,
    currentTime: String,
) {


    val numericInputState = rememberInputFieldState(
        isNumeric = true,
        label = R.string.hint_enter_cost,
        onFieldChange = {})

    val mockInputState =
        rememberInputFieldState(isNumeric = false, label = R.string.hint_enter_cost) {}

    val descriptionInputState = rememberInputFieldState(
        isNumeric = false,
        label = R.string.text_field_enter_task,
        onFieldChange = {})

    Scaffold(topBar = { NotesTopAppBar() }, bottomBar = {
        Controllers(onClickSubmit = {
            viewModel.onEvent(
                HandleShareViewModelEvent.Submit(
                    description = descriptionInputState.fieldValue,
                    cost = InputFieldState.parseAndGet(numericInputState.fieldValue)
                )
            )
        }, onClickCancel = {
            viewModel.onEvent(HandleShareViewModelEvent.Cancel)
        })
    }) { pv ->
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .padding(pv)
                .imePadding()
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Title()
            WrabbyteTimeSection(currentTime = currentTime)
            WrabbyteInteractiveElement(
                inputState = mockInputState,
                title = R.string.text_field_description,
                initialValue = viewModel.currentDescription,
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Body(
                    modifier = modifier,
                    numericState = numericInputState,
                    stringInputState = descriptionInputState,
                )
            }

            SpendConfirmSection(
                modifier = modifier, isCostPayed = viewModel.isCurrentCostIsPaid
            ) {
                viewModel.onEvent(HandleShareViewModelEvent.Bought)
            }


        }
    }
}


@Composable
private fun SpendConfirmSection(
    modifier: Modifier = Modifier,
    isCostPayed: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AssistChip(
            onClick = onClick,label = { Text(text = stringResource(id = R.string.chip_notify_spend)) },
            leadingIcon = {
                if (isCostPayed) Icon(
                    painter = IconHolder.render(IconsHub.confirm).getIcon(),
                    contentDescription = null
                )
            },
            shape = MaterialTheme.shapes.extraLarge
        )
        AnimatedVisibility(visible = isCostPayed) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.text_field_caution_cant_delete_later),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

    }

}

@Composable
private fun Controllers(
    onClickSubmit: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LeeNotesButton(
            onClick = onClickSubmit, title = R.string.button_label_dokay
        )

        LeeNotesButton(
            onClick = onClickCancel, title = R.string.button_label_cancel
        )

    }
}

@Composable
private fun Title() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = IconHolder.render(imageVector = IconsHub.create).getIcon(),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.text_label_intent_parse_screen),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    numericState: InputFieldState,
    stringInputState: InputFieldState,
) {

    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {


            WrabbyteInteractiveElement(
                inputState = stringInputState,
                title = R.string.text_label_title,
                initialValue = "",
                shape = firstElementShape
            )
            WrabbyteInteractiveElement(
                inputState = numericState,
                title = R.string.text_field_cost,
                initialValue = "",
                shape = lastElementShape
            )

        }
    }
}


