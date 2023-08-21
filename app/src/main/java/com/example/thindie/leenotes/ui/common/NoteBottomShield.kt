package com.example.thindie.leenotes.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongParameterList")
@Composable
fun NoteBottomSheet(
    @StringRes title: Int,
    @StringRes alreadySpent: Int,
    @StringRes willSpend: Int,
    state: NoteBottomSheetState,
    sumNotes: String,
    sumCosts: String,
) {

    if (state.state.isVisible) {
        ModalBottomSheet(
            modifier = Modifier
                .height(252.dp)
                .padding(start = 8.dp, end = 8.dp),
            sheetState = state.state,
            shape = RoundedCornerShape(16.dp),
            containerColor = colors.primary,
            contentColor = colors.onTertiary,
            onDismissRequest = {
                state.hideList()
            },
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = title),
                    style = typo.headlineMedium,
                    color = colors.onPrimary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 28.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = alreadySpent), style = typo.titleLarge)
                    Text(text = sumCosts, style = typo.titleLarge)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = willSpend), style = typo.titleLarge)
                    Text(text = sumNotes, style = typo.titleLarge)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberNoteBottomSheetState(
    scope: CoroutineScope = rememberCoroutineScope(),
    sheetState: SheetState = rememberModalBottomSheetState(),
): NoteBottomSheetState {
    return remember(sheetState, scope) {
        NoteBottomSheetState(sheetState, scope)
    }
}

@Stable
@OptIn(ExperimentalMaterial3Api::class)
class NoteBottomSheetState constructor(
    val state: SheetState,
    val scope: CoroutineScope,
) {

    fun hideList() {
        scope.launch {
            state.hide()
        }
    }

    fun showList() {
        scope.launch {
            state.show()
        }
    }

}