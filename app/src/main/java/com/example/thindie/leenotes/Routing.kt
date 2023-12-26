package com.example.thindie.leenotes

import androidx.navigation.NavController
import com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.concreteNoteRoute


fun NavController.concreteNote(argument: String){
    navigate(route = concreteNoteRoute.plus(argument)){
        restoreState = true
        launchSingleTop = true
    }
}