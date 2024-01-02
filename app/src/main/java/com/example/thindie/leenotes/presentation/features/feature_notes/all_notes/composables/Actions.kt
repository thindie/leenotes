package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState

@Composable
fun Actions(state: NoteActionsHubState) {
    if (state.isInputVisible.not()) {
        ActionsNotesFilter(onClick = state::onFilter)
    }
    if (state.shouldShowSendAction) {
        ActionSend(onSend = state::onSend, onClear = state::onClearOutput)
    }
}


@Composable
fun ActionSend(onSend: () -> Unit, onClear: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedIconButton(onSend) {
            Icon(painter = IconHolder.render(IconsHub.send).getIcon(), contentDescription = null)
        }
        OutlinedIconButton(onClick = onClear) {
            Icon(painter = IconHolder.render(IconsHub.cancel).getIcon(), contentDescription = null)
        }
    }

}

@Composable
fun ActionsNotesFilter(onClick: (NoteType) -> Unit) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
            .padding(horizontal = 12.dp)
            .height(40.dp)
    ) {
        FilteringAction(NoteType.SPENT, R.string.chip_bottom_spent, onClick)
        FilteringAction(NoteType.TEMP, R.string.chip_bottom_temp, onClick)
        FilteringAction(NoteType.PRICED, R.string.chip_bottom_priced, onClick)
        FilteringAction(NoteType.ALL, R.string.chip_bottom_all, onClick)
    }
}

@Composable
fun FilteringAction(
    sortType: NoteType,
    @StringRes title: Int,
    onClick: (NoteType) -> Unit,
) {
    AssistChip(
        onClick = { onClick(sortType) },
        label = { Text(text = stringResource(id = title)) })

}
