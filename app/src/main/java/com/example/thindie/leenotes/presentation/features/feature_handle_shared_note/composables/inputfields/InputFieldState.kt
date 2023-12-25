package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Stable
class InputFieldState(val isNumeric: Boolean, val label: Int) {

    var onAgreed : (String) -> Unit = {}

    val keyboardOptions =
        if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Number) else
            KeyboardOptions(keyboardType = KeyboardType.Text)

    var fieldValue by mutableStateOf("")
        private set

    var isInputAgreed by mutableStateOf(false)

    fun onValueChange(string: String) {
        if (string.isBlank()){ isInputAgreed = false }
        fieldValue = string
    }

    val colorTypeConfirmed
        @Composable get() = MaterialTheme.colorScheme.primary

    val colorNotConfirmed  get() =  Color.Green.copy(0.5f)



    fun onAgreeInputClicked() {
        isInputAgreed = true
        onAgreed.invoke(fieldValue)

    }

    companion object {
        fun parseAndGet(string: String): Int {
            return try {
                string.toInt()
            } catch (_: Exception) {
                0
            }
        }
    }

}