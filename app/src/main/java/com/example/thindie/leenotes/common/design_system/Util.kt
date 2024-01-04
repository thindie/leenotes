package com.example.thindie.leenotes.common.design_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlin.random.Random

val containerColorsList
   @Composable get() = listOf(
    MaterialTheme.colorScheme.primaryContainer,
       MaterialTheme.colorScheme.secondaryContainer,
       MaterialTheme.colorScheme.tertiaryContainer,
       MaterialTheme.colorScheme.surfaceVariant
)

@Composable fun getColor() = containerColorsList[Random.nextInt(containerColorsList.size - 1)]