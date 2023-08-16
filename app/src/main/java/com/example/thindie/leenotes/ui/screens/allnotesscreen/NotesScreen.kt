package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFieldState
import com.example.thindie.leenotes.ui.common.inputfields.rememberInputState
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
    searchBarState: NotesInputFieldState = rememberInputState(isSingleLine = true),
    onClickDetails: (Long) -> Unit,
    onClickRemoveAndSaveCost: (Long) -> Unit,
    onClickRemoveAndForgetCost: (Long) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {},
        snackbarHost = {},
        floatingActionButton =
        { NoteFloatingButton(onClickAdd = notesScreenViewModel::onClickedActionButtonForResult) },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = colors.primary,
    ) {
        LazyColumn(modifier = modifier.padding(it)) {

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


@Preview(showBackground = true)
@Composable
fun AllNotesScreenPreview() {
    LeenotesTheme {
        NoteFloatingButton(onClickAdd = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AllNotesScreenPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}