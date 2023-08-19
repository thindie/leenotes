package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val concreteNote = "concreteNote"

fun NavGraphBuilder.concreteNote(id: () -> Long) {
    composable(route = concreteNote) {
        ConcreteNoteScreen(id = id.invoke(), onClickDismiss = { /*TODO*/ }, onClickConfirm = {} )
    }
}