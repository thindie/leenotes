package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.NoteComposable
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.common.design_system.listShape
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.NoteActionsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.NoteSimpleUnit
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NotesScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesScreenViewModel,
    onSelectArgument: (String) -> Unit,
) {

    val screenState by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
        Scaffold(modifier = modifier.imePadding(), bottomBar = {
            NoteActionsHub(onEvent = viewModel::onNoteScreenEvent)
        }) {
            LazyColumn(
                modifier = modifier
                    .padding(it)
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) { ->
                stickyHeader { NotesTitle() }
                item { StubComposable() }
                itemsIndexed(items = screenState.notesList, key = { _, item ->
                    item.id
                }) { i, note ->
                    NoteComposable(
                        modifier = modifier
                            .padding(horizontal = 12.dp),
                        shape = listShape(i, screenState.notesList.size),
                        color = getColor(),
                    ) { modifier ->
                        NoteSimpleUnit(note = note,
                            modifier = modifier,
                            onClickNote = { onSelectArgument(note.id.toString()) })
                    }
                }
                item { StubComposable() }
            }
    }
}

@Composable
private fun NotesTitle() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            text = stringResource(id = R.string.text_label_all_notes),
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.onBackground)
        )
    }


}

@Composable
private fun StubComposable() {
    Spacer(modifier = Modifier.height(20.dp))
}
