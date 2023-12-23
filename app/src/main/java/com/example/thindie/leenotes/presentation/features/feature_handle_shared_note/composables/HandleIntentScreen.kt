package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.common.IconHolder
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel

@Composable
fun HandleIntentScreen(modifier: Modifier = Modifier, viewModel: HandleShareViewModel) {
    Surface {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NotesTopAppBar {

            }
            Spacer(modifier = modifier.height(8.dp))
            Title()
            Body(
                transferredString = viewModel.currentDescription,
                onProvideCosts = viewModel::onSelectCost,
                onProvideTitle = viewModel::onProvideTitle
            )
            Spacer(modifier = modifier.weight(1f, true))
            Controllers(
                onClickSubmit = viewModel::onClickHandle, onClickCancel = viewModel::onClickCancel
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
        Button(
            onClickSubmit,
            shape = MaterialTheme.shapes.large,
        ) {
            Text(
                text = stringResource(R.string.button_label_dokay),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Button(
            onClickCancel,
            shape = MaterialTheme.shapes.large,
        ) {
            Text(
                text = stringResource(R.string.button_label_cancel),
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }
}

@Composable
private fun ColumnScope.Title() {

    Text(
        modifier = Modifier.align(CenterHorizontally),
        text = stringResource(R.string.text_label_intent_parse_screen),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    transferredString: String,
    onProvideCosts: (Int) -> Unit,
    onProvideTitle: (String) -> Unit,
) {

    ElevatedCard(modifier = modifier.padding(horizontal = 8.dp, vertical = 20.dp)) {
        Text(text = transferredString, style = MaterialTheme.typography.labelSmall)
        Spacer(modifier = modifier.height(20.dp))
        InputRow(getValue = onProvideTitle)
        Spacer(modifier = modifier.height(20.dp))
        InputRowNumbers(getValue = onProvideCosts)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputRow(modifier: Modifier = Modifier, getValue: (String) -> Unit) {
    var fieldValue by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = { fieldValue = it }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = onValueChange,
            shape = MaterialTheme.shapes.extraLarge,
            label = { Text(text = stringResource(id = R.string.text_field_enter_task)) }
        )
        IconButton(onClick = { getValue(fieldValue); keyboardController?.hide() }) {
            Icon(painter = IconHolder.render(IconsHub.confirm).getIcon(), contentDescription = null)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputRowNumbers(modifier: Modifier = Modifier, getValue: (Int) -> Unit) {
    var fieldValue by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = { fieldValue = it }
    val keyboardController = LocalSoftwareKeyboardController.current
    fun parseAndGet(string: String): Int {
        return try {
            string.toInt()
        } catch (_: Exception) {
            0
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = fieldValue,
            onValueChange = onValueChange,
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            label = { Text(text = stringResource(id = R.string.text_field_cost)) }
        )
        IconButton(onClick = { getValue(parseAndGet(fieldValue)); keyboardController?.hide() }) {
            Icon(painter = IconHolder.render(IconsHub.confirm).getIcon(), contentDescription = null)
        }
    }
}
