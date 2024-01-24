package com.example.thindie.leenotes.common.design_system.input_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberInputFieldState(
    isNumeric: Boolean,
    label: Int,
    onFieldChange: () -> Unit,
): InputFieldState {
    return remember {
        InputFieldState(isNumeric, label, onFieldChange)
    }
}