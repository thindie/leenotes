package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.domain.NoteType
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.composables.noteshubstate.NoteActionsHubState

@Composable
fun Actions(state: NoteActionsHubState) {
    if (state.shouldShowSendAction) {
        ActionSend(onSend = state::onSend, onClear = state::onClearOutput)

    } else {
        ActionsNotesFilter(onClick = state::onFilter)
    }
}


@Composable
fun ActionSend(onSend: () -> Unit, onClear: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(onSend) {
            Icon(painter = IconHolder.render(IconsHub.send).getIcon(), contentDescription = null)
        }
        IconButton(onClick = onClear) {
            Icon(painter = IconHolder.render(IconsHub.cancel).getIcon(), contentDescription = null)
        }
    }

}

@Composable
fun ActionsNotesFilter(onClick: (NoteType) -> Unit) {
    val padding = Modifier.padding(horizontal = 8.dp)
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        FilteringAction(NoteType.COST, IconHolder.render(IconsHub.costs), onClick)
        Spacer(padding)
        FilteringAction(NoteType.NON_SPECIFIED, IconHolder.render(IconsHub.menu), onClick)
        Spacer(padding)
        FilteringAction(NoteType.HAS_HYPER, IconHolder.render(IconsHub.web), onClick)
    }
}

@Composable
fun FilteringAction(
    sortType: NoteType,
    holder: IconHolder,
    onClick: (NoteType) -> Unit,
) {
    IconButton(onClick = { onClick(sortType) }) {
        Icon(painter = holder.getIcon(), contentDescription = null)
    }

}
