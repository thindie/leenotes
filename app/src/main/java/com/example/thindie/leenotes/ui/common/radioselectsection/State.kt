package com.example.thindie.leenotes.ui.common.radioselectsection

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberNotesRadioItemsState(
    list: List<NoteRadioUnit>,
    scope: CoroutineScope = rememberCoroutineScope(),
): NotesRadioItemState {
    return remember(list, scope) {
        NotesRadioItemState(list, scope)
    }
}


@Composable
fun NotesRadioSection(
    state: NotesRadioItemState,
    onClick: (NoteRadioUnit) -> Unit,
) {

    val listState =
        state.currentList.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    NotesRadioSection(list = listState.value.items, isActive = {
        it.isActive
    }, title = {
        stringResource(id = it.title)
    }, onClick = {
        onClick(it)
        state.onClick(it)
    })
}

class NotesRadioItemState(list: List<NoteRadioUnit>, coroutineScope: CoroutineScope) {

    private val _currentList = MutableStateFlow(RadioItems(list))
    val currentList = _currentList.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        RadioItems(list)
    )

    fun onClick(unit: NoteRadioUnit) {
        val bufferList = _currentList.value.items.map { listUnit ->
            if (listUnit.hashCode() == unit.hashCode()) listUnit.copy(isActive = true)
            else listUnit.copy(isActive = false)
        }
        _currentList.value = RadioItems(bufferList)
    }

    data class RadioItems(val items: List<NoteRadioUnit>)
}

data class NoteRadioUnit(
    @StringRes val title: Int,
    val isActive: Boolean,
    val filterState: MainViewModel.Filter
)