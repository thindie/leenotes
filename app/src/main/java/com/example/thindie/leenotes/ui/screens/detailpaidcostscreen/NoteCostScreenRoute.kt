package com.example.thindie.leenotes.ui.screens.detailpaidcostscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val noteCostScreen = "costs"

fun NavGraphBuilder.notesCostsScreen(onClickMenu: () -> Unit, onClickBack: () -> Unit) {
    composable(noteCostScreen) {
        NoteCostScreen(onClickMenu = { onClickMenu() }) {
            onClickBack()
        }
    }
}