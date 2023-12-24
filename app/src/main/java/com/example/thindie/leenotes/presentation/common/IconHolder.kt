package com.example.thindie.leenotes.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

sealed class IconHolder {
    data class DrawableResIcon(@DrawableRes val pic: Int) : IconHolder()
    data class VectorPainterIcon(val icon: ImageVector) : IconHolder()

    @Composable
    fun getIcon(): Painter {
        return when (this) {
            is DrawableResIcon -> painterResource(pic)
            is VectorPainterIcon -> rememberVectorPainter(icon)
        }
    }

    companion object {
        @Composable
        fun render(@DrawableRes int: Int) = DrawableResIcon(int)

        @Composable
        fun render(imageVector: ImageVector) = VectorPainterIcon(imageVector)
    }
}