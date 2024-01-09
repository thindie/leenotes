package com.example.thindie.leenotes.presentation.features.feature_note_stats.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.getColor
import com.example.thindie.leenotes.domain.SummaryStep
import com.example.thindie.leenotes.domain.entities.Summary
import com.example.thindie.leenotes.presentation.IconsHub
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsScreenEvent
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsViewModel

@Composable
fun NotesStatisticsScreen(modifier: Modifier = Modifier, viewModel: NotesStatisticsViewModel) {
    val state by viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChronoSection(state.summary)
        TotalNotes(int = state.summary?.totalNotes)
        TotalSpent(int = state.summary?.totalSpent)
        TotalTempAndPlanned(
            temp = state.summary?.tempNotes,
            plannedSpent = state.summary?.spentNotes,
            plannedCosts = state.summary?.plannedCosts
        )
        TopSpentAndTopPlanned(
            topSpent = state.summary?.topSpent,
            topPlanned = state.summary?.topPlanned
        )


        Spacer(modifier = modifier.weight(1f, true))
        ControlRow(onEvent = viewModel::onEvent, state.summary)
    }

}

@Composable
fun ChronoSection(
    summary: Summary?,
) {
    if (summary != null) {
        when (summary.step) {
            SummaryStep.DAY -> {
                    Text(
                        text = stringResource(
                            R.string.text_label_summary_day_chrono,
                            summary.summaryDay,
                            summary.summaryMonth,
                            summary.summaryYear
                        )
                    )
            }

            SummaryStep.MONTH -> Text(
                text = stringResource(
                    R.string.text_label_summary_day_chrono,
                    "",
                    summary.summaryMonth,
                    summary.summaryYear
                )
            )
            SummaryStep.YEAR -> Text(
                text = stringResource(
                    R.string.text_label_summary_day_chrono,
                    "",
                    "",
                    summary.summaryYear
                )
            )
            SummaryStep.ALL -> Text(text = stringResource(id = R.string.text_label_summary_all))
        }
    }
}

@Composable
fun ControlRow(onEvent: (NotesStatisticsScreenEvent) -> Unit, summary: Summary?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedIconButton(onClick = { onEvent(NotesStatisticsScreenEvent.BackWard) }) {
            Icon(
                painter = IconHolder.render(IconsHub.left).getIcon(),
                contentDescription = null
            )
        }

        OutlinedButton(onClick = { onEvent(NotesStatisticsScreenEvent.All) }) {
            Text(text = stringResource(R.string.text_field_all))
        }


        OutlinedIconButton(
            onClick = { onEvent(NotesStatisticsScreenEvent.Forward) },
            enabled = summary?.isCurrentTimeSummary == false
        ) {
            Icon(
                painter = IconHolder.render(IconsHub.right).getIcon(),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun TopSpentAndTopPlanned(
    modifier: Modifier = Modifier,
    topSpent: List<String>?,
    topPlanned: List<String>?,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        LazyColumn(
            modifier = modifier
                .background(getColor())
                .fillMaxWidth(0.5f)
        ) {
            item {
                Text(
                    modifier = modifier.padding(bottom = 20.dp),
                    text = stringResource(id = R.string.text_label_top_from_spent),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            items(topSpent.orEmpty()) {
                Text(text = it)
            }
        }
    }
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        LazyColumn(
            modifier = modifier
                .background(getColor())
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.End
        ) {

            item {
                Text(
                    modifier = modifier.padding(bottom = 20.dp),
                    text = stringResource(id = R.string.text_label_top_from_planned),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            items(topPlanned.orEmpty()) {
                Text(text = it)
            }
        }
    }
}


@Composable
private fun TotalNotes(modifier: Modifier = Modifier, int: Int?) {
    if (int != null)
        Row(modifier = modifier.background(getColor())) {
            Text(
                text = stringResource(id = R.string.text_label_total_notes, int),
                style = MaterialTheme.typography.titleLarge
            )
        }
}

@Composable
private fun TotalSpent(modifier: Modifier = Modifier, int: Double?) {
    if (int != null)
        Row(modifier = modifier.background(getColor())) {
            Text(
                text = stringResource(id = R.string.text_label_total_spent, int),
                style = MaterialTheme.typography.titleLarge
            )
        }
}

@Composable
private fun TotalTempAndPlanned(
    modifier: Modifier = Modifier,
    temp: Int?,
    plannedSpent: Int?,
    plannedCosts: Double?,
) {

    Row(
        modifier = modifier
            .background(getColor())
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (temp != null)
            Text(
                text = stringResource(id = R.string.text_label_total_temp_notes, temp),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        if (plannedSpent != null)
            Text(
                text = stringResource(id = R.string.text_label_planned_to_spent, plannedSpent),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )

    }
    Row(
        modifier = modifier
            .background(getColor())
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        if (plannedCosts != null)
            Text(
                text = stringResource(id = R.string.text_label_planned_costs, plannedCosts),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
    }
}