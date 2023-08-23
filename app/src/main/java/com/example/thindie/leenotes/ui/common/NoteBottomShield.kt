package com.example.thindie.leenotes.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.MainViewModel
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.common.radioselectsection.NoteRadioUnit
import com.example.thindie.leenotes.ui.common.radioselectsection.NotesRadioItemState
import com.example.thindie.leenotes.ui.common.radioselectsection.NotesRadioSection
import com.example.thindie.leenotes.ui.common.radioselectsection.rememberNotesRadioItemsState
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val unitByCurrentMonth = NoteRadioUnit(
    R.string.text_field_costs_now,
    isActive = true,
    filterState = MainViewModel.Filter.CURRENT
)
val unitByLastMonth = NoteRadioUnit(
    R.string.text_field_costs_last,
    isActive = false,
    filterState = MainViewModel.Filter.LAST
)
val unitByTotal = NoteRadioUnit(
    R.string.text_field_costs_total,
    isActive = false,
    filterState = MainViewModel.Filter.ALL
)
val costGroups = listOf(unitByLastMonth, unitByCurrentMonth, unitByTotal)

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongParameterList")
@Composable
fun NoteBottomSheet(
    @StringRes title: Int,
    @StringRes alreadySpent: Int,
    @StringRes willSpend: Int,
    @StringRes navigationButtonTitle: Int,
    state: NoteBottomSheetState,
    radioSectionState: NotesRadioItemState = rememberNotesRadioItemsState(list = costGroups),
    sumNotes: String,
    sumCosts: String,
    onClickedGroupChip: (MainViewModel.Filter) -> Unit,
    onClickedNavigationButton: () -> Unit,
) {

    if (state.state.isVisible) {
        ModalBottomSheet(
            modifier = Modifier
                .height(352.dp)
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

                NotesRadioSection(onClick = {
                    onClickedGroupChip(it.filterState)
                }, state = radioSectionState)

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                val localRowContentPadding = PaddingValues(horizontal = 20.dp, vertical = 5.dp)


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_archived),
                        contentDescription = "",
                        tint = colors.onSecondary
                    )
                    Text(
                        modifier = Modifier.padding(localRowContentPadding),
                        text = stringResource(id = alreadySpent),
                        style = typo.titleLarge,
                        color = colors.onPrimary
                    )
                    Text(
                        modifier = Modifier.padding(localRowContentPadding),
                        text = sumCosts,
                        style = typo.titleLarge,
                        color = colors.onPrimary
                    )
                    NotesButton(
                        modifier = Modifier
                            .width(120.dp)
                            .height(40.dp),
                        title = navigationButtonTitle,
                        isOutlined = true,
                        isMinorButton = true
                    ) {
                        onClickedNavigationButton()
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_will_spend),
                        contentDescription = "",
                        tint = colors.onSecondary
                    )
                    Text(
                        modifier = Modifier.padding(localRowContentPadding),
                        text = stringResource(id = willSpend),
                        style = typo.titleLarge,
                        color = colors.onPrimary
                    )
                    Text(
                        modifier = Modifier.padding(localRowContentPadding),
                        text = sumNotes,
                        style = typo.titleLarge,
                        color = colors.onPrimary
                    )
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
    private val scope: CoroutineScope,
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