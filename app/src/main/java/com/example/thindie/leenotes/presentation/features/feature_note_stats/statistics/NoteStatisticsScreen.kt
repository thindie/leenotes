package com.example.thindie.leenotes.presentation.features.feature_note_stats.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsViewModel

@Composable
fun NotesStatisticsScreen(modifier: Modifier = Modifier, viewModel: NotesStatisticsViewModel) {
    val state by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        TotalNotes(int = state.summary?.totalNotes)
        TotalSpent(int = state.summary?.totalSpent)
        TotalTempAndPlanned(
            temp = state.summary?.totalTempNotes,
            plannedSpent = state.summary?.totalPlannedToSpentNotes,
            plannedCosts = state.summary?.totalPlannedCosts
        )
        TopSpentAndTopPlanned(
            topSpent = state.summary?.topSpent,
            topPlanned = state.summary?.topPlanned
        )
    }

}

@Composable
fun TopSpentAndTopPlanned(
    modifier: Modifier = Modifier,
    topSpent: List<String>?,
    topPlanned: List<String>?,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        LazyColumn(modifier = modifier.fillMaxWidth(0.5f)) {
            items(topSpent.orEmpty()) {
                Text(text = it)
            }
        }
        LazyColumn(modifier = modifier.fillMaxWidth(0.5f)) {
            items(topPlanned.orEmpty()) {
                Text(text = it)
            }
        }
    }
}

@Composable
private fun TotalNotes(modifier: Modifier = Modifier, int: Int?) {
    if (int != null)
        Row(modifier = modifier) {
            Text(text = stringResource(id = R.string.text_label_total_notes, int))
        }
}

@Composable
private fun TotalSpent(modifier: Modifier = Modifier, int: Double?) {
    if (int != null)
        Row(modifier = modifier) {
            Text(text = stringResource(id = R.string.text_label_total_spent, int))
        }
}

@Composable
private fun TotalTempAndPlanned(
    modifier: Modifier = Modifier,
    temp: Int?,
    plannedSpent: Int?,
    plannedCosts: Double?,
) {

    Row(modifier = modifier) {
        if (temp != null)
            Text(text = stringResource(id = R.string.text_label_total_temp_notes, temp))
        if (plannedSpent != null)
            Text(text = stringResource(id = R.string.text_label_planned_to_spent, plannedSpent))
        if (plannedCosts != null)
            Text(text = stringResource(id = R.string.text_label_planned_costs, plannedCosts))
    }
}