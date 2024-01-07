package com.example.thindie.leenotes.presentation.features.feature_notes.all_notes

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NotesScreenComponent
import com.example.thindie.leenotes.presentation.features.feature_notes.viewodel.NotesScreenViewModel

const val allNotesRoute = "allNotes"

fun NavGraphBuilder.allNotesScreen(onClickMenu: () -> Unit, onClickConcreteNote: (String) -> Unit) {
    composable(route = allNotesRoute) {
        val context = LocalContext.current
        val dependenciesProvider = initDaggerComponent(context = context)
        if (dependenciesProvider != null) {
            val viewModelFactory = dependenciesProvider.provideFactory()
            val viewModel: NotesScreenViewModel = viewModel(factory = viewModelFactory)
            NotesScreen(viewModel = viewModel, onSelectArgument = onClickConcreteNote)
        }

    }
}


@Composable
private fun initDaggerComponent(context: Context): NotesScreenComponent? {
    val currentApp = context as? App
    return if (currentApp != null) {
        NotesScreenComponent.init(currentApp.getAppComponent())
    } else null
}