package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.LeeNotesButton
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.InputFieldState
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.InputRow
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields.rememberInputFieldState
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModelEvent

@Composable
fun HandleIntentScreen(modifier: Modifier = Modifier, viewModel: HandleShareViewModel) {


    val numericInputState =
        rememberInputFieldState(isNumeric = true, label = R.string.text_field_cost)

    val descriptionInputState =
        rememberInputFieldState(isNumeric = false, label = R.string.text_field_enter_task)

    Scaffold(topBar = { NotesTopAppBar {} }) {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 20.dp)
                .padding(it)
                .imePadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Title()
            Body(
                transferredString = viewModel.currentDescription,
                numericState = numericInputState,
                stringInputState = descriptionInputState
            )
            Spacer(modifier = modifier.weight(1f, true))
            Controllers(
                onClickSubmit = {
                    viewModel.onEvent(
                        HandleShareViewModelEvent.Submit(
                            description = descriptionInputState.fieldValue,
                            cost = InputFieldState.parseAndGet(numericInputState.fieldValue)
                        )
                    )
                }, onClickCancel = {
                    viewModel.onEvent(HandleShareViewModelEvent.Cancel)
                }
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
            onClick = onClickSubmit,
            title = R.string.button_label_dokay
        )

        LeeNotesButton(
            onClick = onClickCancel,
            title = R.string.button_label_cancel
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
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    transferredString: String,
    numericState: InputFieldState,
    stringInputState: InputFieldState,
) {

    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier
                .padding(5.dp)
        ) {
            Text(
                text = transferredString,
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = modifier.height(3.dp))
            InputRow(state = numericState)
            Spacer(modifier = modifier.height(3.dp))
            InputRow(state = stringInputState)
        }


    }
}


