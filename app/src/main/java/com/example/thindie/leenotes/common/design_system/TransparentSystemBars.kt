package com.example.thindie.leenotes.common.design_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBars(isInDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isInDarkTheme
    val color = MaterialTheme.colorScheme.background

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}