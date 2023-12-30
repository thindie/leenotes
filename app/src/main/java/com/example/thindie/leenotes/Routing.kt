package com.example.thindie.leenotes

import androidx.navigation.NavController
import com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.concreteNoteRoute
import com.example.thindie.leenotes.presentation.features.feature_notes.all_notes.routeAllNotes


fun NavController.concreteNote(argument: String){
    navigate(route = concreteNoteRoute.plus(argument)){
        restoreState = true
        launchSingleTop = true
    }
}

fun NavController.allNotes(){
    navigate(route = routeAllNotes){
        restoreState = true
        launchSingleTop = true
    }
}