package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val routeAllNotes = "allNotes"
fun NavGraphBuilder.allNotesScreen(onClickMenu: () -> Unit, onClickConcreteNote: (Long) -> Unit) {
    composable(route = routeAllNotes) {

    }
}