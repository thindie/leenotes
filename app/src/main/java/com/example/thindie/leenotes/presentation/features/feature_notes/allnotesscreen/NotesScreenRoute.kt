package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.viewodel.NotesScreenViewModel
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NotesScreenComponent

const val routeAllNotes = "allNotes"

fun NavGraphBuilder.allNotesScreen(onClickMenu: () -> Unit, onClickConcreteNote: (Long) -> Unit) {
    composable(route = routeAllNotes) {
        val app: App? = LocalContext.current as? App
        if (app != null){
            val dependenciesProvider = DependenciesProvider.get(app)
            val factory = provideFactoryFromDaggerComponent(dependenciesProvider)

            val viewModel: NotesScreenViewModel = viewModel(factory = factory)
        }

    }
}

private fun provideFactoryFromDaggerComponent(dependenciesProvider: DependenciesProvider) =
    NotesScreenComponent.init(dependenciesProvider).provideFactory()





