package com.example.thindie.leenotes.presentation.features.feature_note_stats.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsViewModel

@Composable
fun NotesStatisticsScreen(viewModel: NotesStatisticsViewModel) {
    val state by
        viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Column {
        Text(text = state.summary?.totalPlannedCosts.toString())

        state.summary?.topSpent?.forEach {
            Text(text = it)
        }
    }

}