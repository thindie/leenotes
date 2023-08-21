package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val concreteNote = "concreteNote"

fun NavGraphBuilder.concreteNote(id: () -> Long, onClickBack: () -> Unit) {
    composable(route = concreteNote) {
        ConcreteNoteScreenState(
            id = id.invoke(),
            onClickDismiss = onClickBack,
            onClickConfirm =  onClickBack)
    }
}