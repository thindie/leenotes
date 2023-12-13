package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val concreteNote = "concreteNote"

fun NavGraphBuilder.concreteNote(
    onClickBrowse: (String) -> Unit,
    id: () -> Long,
    onClickMenu: () -> Unit,
    onClickBack: () -> Unit,
) {
    composable(route = concreteNote) {

    }
}