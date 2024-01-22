package com.example.thindie.leenotes.common.design_system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("LongParameterList")
@Composable
fun NoteComposable(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    elevation: Dp = 0.dp,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable (Modifier) -> Unit,
) {


  val cardElevation =  CardDefaults.cardElevation(
        defaultElevation = 3.dp.plus(elevation),
        pressedElevation = 1.dp.plus(elevation),
        focusedElevation = 4.dp.plus(elevation),
        hoveredElevation = 2.dp.plus(elevation),
        draggedElevation = 2.dp.plus(elevation),
        disabledElevation = 0.dp.plus(elevation)
    )



    ElevatedCard(
        shape = shape,
        modifier = modifier,
        elevation = cardElevation,
    ) {
        content(
            Modifier
                .padding(contentPadding)

        )
    }
}