package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.ui.common.NotesButton
import com.example.thindie.leenotes.ui.common.NotesTopAppBar
import com.example.thindie.leenotes.ui.common.inputfields.DigitInputState
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputField
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFieldState
import com.example.thindie.leenotes.ui.common.inputfields.rememberDigitInputState
import com.example.thindie.leenotes.ui.common.inputfields.rememberInputState
import com.example.thindie.leenotes.ui.dialogs.CoastalEntranceDialogContent
import com.example.thindie.leenotes.ui.dialogs.NotesDialog
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.seedColor
import com.example.thindie.leenotes.ui.theme.typo

@Suppress("LongParameterList")
@Composable
fun ConcreteNoteScreen(
    modifier: Modifier = Modifier,
    note: Note,
    body: NotesInputFieldState = rememberInputState(
        initialValue = note.body,
        isSingleLine = false,
    ),
    attachLink: NotesInputFieldState = rememberInputState(
        initialValue = note.hyperLink,
        isSingleLine = true,
        supportingText = R.string.text_field_attach_link
    ),
    tagShadow: NotesInputFieldState = rememberInputState(
        initialValue = note.tagShadow,
        isSingleLine = true,
        supportingText = R.string.text_field_tags_shadow
    ),
    currencyInputFieldState: DigitInputState = rememberDigitInputState(
        isSingleLine = true,
        initialValue = note.cost.toString()
    ),
    onConfirmUpdate: (Note) -> Unit,
    onClickMenu: () -> Unit,
    onClickDismiss: () -> Unit,
) {
    val shouldShowCoastalDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = { NotesTopAppBar(action = { onClickMenu() }) })
    {
        Column(
            modifier = modifier
                .padding(it)
        ) {
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 40.dp),
                        text = stringResource(
                            id = R.string.text_field_created,
                            note.createdAt
                        ),
                        style = typo.labelLarge, color = colors.onSecondaryContainer
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, bottom = 40.dp),
                        text = note.title,
                        style = typo.headlineLarge
                    )
                }

                item {
                    NotesInputField(state = body)
                }

                item {
                    NotesInputField(state = attachLink)
                }

                item {
                    NotesInputField(state = tagShadow)
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.clickable { shouldShowCoastalDialog.value = true },
                            text = stringResource(id = R.string.text_field_will_spend),
                            style = typo.headlineSmall
                        )
                        Text(
                            text = currencyInputFieldState.fieldState.value,
                            style = typo.headlineMedium,
                            color = seedColor
                        )
                        Image(
                            modifier = Modifier.clickable { currencyInputFieldState.onValueChange("") },
                            painter = painterResource(id = R.drawable.icon_dismiss),
                            contentDescription = ""
                        )
                    }
                }

                item {
                    NotesButton(title = R.string.button_label_shuffled, isOutlined = true) {
                        onClickDismiss()
                    }
                    NotesButton(title = R.string.button_label_good, isOutlined = false) {
                        val newNote = note.noteUpdater(
                            body = body.fieldState.value,
                            cost = currencyInputFieldState.digitValue(),
                            tagShadow = tagShadow.fieldState.value,
                            hyperLink = attachLink.fieldState.value
                        )
                        onConfirmUpdate(newNote)
                    }
                }
            }
        }
        if (shouldShowCoastalDialog.value) {
            NotesDialog(onDismissRequest = {
                currencyInputFieldState.onResetWidthAndState()
                shouldShowCoastalDialog.value = false
            }) {
                CoastalEntranceDialogContent(
                    dialogTitle = R.string.text_field_will_spend,
                    dismissButtonTitle = R.string.button_label_oh_no,
                    confirmButtonTitle = R.string.button_label_cacheen,
                    currency = R.string.text_field_rubles,
                    onClickDismiss = {
                        currencyInputFieldState.onResetWidthAndState()
                        shouldShowCoastalDialog.value = false
                    },
                    currencyInputField = currencyInputFieldState,
                    onClickConfirm = {
                        currencyInputFieldState.onValueChange(it)
                        shouldShowCoastalDialog.value = false
                    }
                )
            }
        }
    }
}

@Suppress("LongParameterList")
@Composable
fun ConcreteNoteScreenState(
    id: Long,
    concreteNoteScreenViewModel: ConcreteNoteScreenViewModel = hiltViewModel(),
    onClickDismiss: () -> Unit,
    onClickMenu:() -> Unit,
    onClickConfirm: () -> Unit,
) {
    concreteNoteScreenViewModel.onClickDetail(id)

    val concreteNoteState =
        concreteNoteScreenViewModel.concreteNoteScreenState.collectAsStateWithLifecycle(
            minActiveState = Lifecycle.State.RESUMED
        )

    concreteNoteState.value?.let { notNullNote ->

        ConcreteNoteScreen(
            note = notNullNote,
            onConfirmUpdate = { concreteNoteScreenViewModel.onConfirmUpdateNote(it); onClickConfirm() },
            onClickMenu = onClickMenu,
            onClickDismiss = onClickDismiss
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ConcreteNoteScreenPreview() {
    LeenotesTheme {

    }
}

@Preview(showBackground = true)
@Composable
fun ConcreteNoteScreenPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}