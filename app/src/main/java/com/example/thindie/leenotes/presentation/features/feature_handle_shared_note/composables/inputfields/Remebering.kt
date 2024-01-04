package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields

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