package com.example.thindie.leenotes

import androidx.navigation.NavController
import com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.concreteNoteRoute
import com.example.thindie.leenotes.presentation.features.feature_note_stats.statistics.statisticsRoute
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.allNotesRoute


fun NavController.concreteNote(argument: String) {
    go(concreteNoteRoute.plus(argument))
}

fun NavController.allNotes() {
    go(allNotesRoute)
}

fun NavController.statistics() {
    go(statisticsRoute)
}

private fun NavController.go(route: String) {
    navigate(route = route) {
        restoreState = true
        launchSingleTop = true
    }
}