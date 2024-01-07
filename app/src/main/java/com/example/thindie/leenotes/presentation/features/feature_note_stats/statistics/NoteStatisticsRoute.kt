package com.example.thindie.leenotes.presentation.features.feature_note_stats.statistics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.presentation.features.feature_note_stats.di.NoteStatisticsComponent
import com.example.thindie.leenotes.presentation.features.feature_note_stats.viewmodel.NotesStatisticsViewModel

const val statisticsRoute = "statisticsRoute"

fun NavGraphBuilder.statisticsScreen() {
    composable(route = statisticsRoute) {
        val context = LocalContext.current
        val daggerComponent: NoteStatisticsComponent? = initDaggerComponent(context)

        if (daggerComponent != null) {
            val factory = daggerComponent.getViewModelFactory()
            val viewModel: NotesStatisticsViewModel = viewModel(factory = factory)
            NotesStatisticsScreen(viewModel)
        }
    }
}

@Composable
private fun initDaggerComponent(context: Context): NoteStatisticsComponent? {
    val currentApp = context as? App
    return if (currentApp != null) {
        NoteStatisticsComponent.init(currentApp.getAppComponent())
    } else null
}