package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.ui.common.NoteCard
import com.example.thindie.leenotes.ui.common.NotesControllableField
import com.example.thindie.leenotes.ui.common.NotesTopAppBar
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFieldState
import com.example.thindie.leenotes.ui.common.inputfields.rememberInputState
import com.example.thindie.leenotes.ui.common.rememberNoteCardState
import com.example.thindie.leenotes.ui.dialogs.ConfirmDismissDialog
import com.example.thindie.leenotes.ui.dialogs.NotesDialog
import com.example.thindie.leenotes.ui.dialogs.TaskDialogContent
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo

@Suppress("LongParameterList")
@Composable
fun AllNotesScreen(
    modifier: Modifier = Modifier,
    notesScreenViewModel: NotesScreenViewModel = hiltViewModel(),
    searchBarState: NotesInputFieldState = rememberInputState(
        isSingleLine = true,
        hint = R.string.text_field_hint_search,
        leadingIcon = R.drawable.icon_search
    ),
    onClickDetails: (Long) -> Unit,
) {
    val shouldShowRemovalDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = { NotesTopAppBar(action = {}) },
        snackbarHost = {},
        floatingActionButton =
        { NoteFloatingButton(onClickAdd = notesScreenViewModel::onClickedActionButtonForResult) },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = colors.primary,
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NotesControllableField(
                title = R.string.text_field_cancel,
                state = searchBarState
            )
            NotesList(
                notes = emptyList(),
                onClickDetails = onClickDetails,
                onClickRemove = { noteId ->
                    shouldShowRemovalDialog.value = true
                }
            )
        }

    }
    if (shouldShowRemovalDialog.value) {
        NotesDialog(onDismissRequest = { shouldShowRemovalDialog.value = false }) {
            ConfirmDismissDialog(
                headline = R.string.text_field_remove,
                supportingHeadline = R.string.text_field_remember_costs,
                confirmButton = R.string.button_label_remember,
                dismissButton = R.string.button_label_offcost,
                representValue = "",
                onClickConfirm = {
                    notesScreenViewModel.onConfirmSaveCosts()
                    shouldShowRemovalDialog.value = false }) {
                shouldShowRemovalDialog.value = false
                notesScreenViewModel.onDismissSaveCosts()
            }
        }
    }
}


@Composable
private fun NoteFloatingButton(
    modifier: Modifier = Modifier,
    headlineState: NotesInputFieldState = rememberInputState(isSingleLine = true),
    bodyState: NotesInputFieldState = rememberInputState(isSingleLine = true),
    onClickAdd: (Note) -> Unit,
) {

    val shouldShowAddingDialog = remember() {
        mutableStateOf(false)
    }


    FloatingActionButton(
        modifier = modifier,
        onClick = {
            shouldShowAddingDialog.value = true
        },
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 5.dp)
    ) {
        Text(
            text = stringResource(R.string.fab_symbol),
            style = typo.headlineMedium,
            color = colors.onPrimary
        )
    }
    if (shouldShowAddingDialog.value) {
        NotesDialog(onDismissRequest = { shouldShowAddingDialog.value = false }) {
            TaskDialogContent(
                headLine = headlineState,
                body = bodyState,
                onClickPlan = { headLine, body ->
                    onClickAdd(Note(title = headLine, body = body))
                    shouldShowAddingDialog.value = false
                    headlineState.clearField()
                    bodyState.clearField()
                }) {
                shouldShowAddingDialog.value = false
                headlineState.clearField()
                bodyState.clearField()
            }
        }
    }

}


@Composable
private fun NotesList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onClickDetails: (Long) -> Unit,
    onClickRemove: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, top = 18.dp, bottom = 4.dp),
        contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp)
    ) {
        items(notes, key = { note -> note.timeStamp }) { item: Note ->
            NoteCard(
                state = rememberNoteCardState(),
                title = item.title,
                time = item.noteCreateAt,
                body = item.body,
                noteId = item.timeStamp,
                onClickDetails = onClickDetails,
                onClickRemove = onClickRemove,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AllNotesScreenPreview() {
    LeenotesTheme {
        AllNotesScreen(
            onClickDetails = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AllNotesScreenPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}