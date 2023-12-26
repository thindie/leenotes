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



    val keyboardOptions =
        if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Number) else
            KeyboardOptions(keyboardType = KeyboardType.Text)

    var fieldValue by mutableStateOf("")
        private set



    fun onValueChange(string: String) {

        fieldValue = string
    }


    companion object {
        fun parseAndGet(string: String): Int? {
            return try {
                string.toInt()
            } catch (_: Exception) {
                null
            }
        }
    }

}