package com.example.thindie.leenotes.ui.screens.allnotesscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val routeAllNotes = "allNotes"
fun NavGraphBuilder.allNotesScreen(onClickConcreteNote: (Long) -> Unit) {
    composable(route = routeAllNotes) {
        AllNotesScreen(onClickDetails = onClickConcreteNote)
    }
}