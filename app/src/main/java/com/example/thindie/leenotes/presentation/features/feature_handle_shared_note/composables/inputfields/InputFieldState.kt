package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.inputfields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Stable
class InputFieldState(
    val isNumeric: Boolean,
    val label: Int,
    private val onFieldChange: () -> Unit,
) {

    var shouldShowLabel by mutableStateOf(false)
        private set

    val canBeUseAsLink by derivedStateOf { matchesWebLink() }

    private var isInit = false

    val keyboardOptions =
        if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Number) else
            KeyboardOptions(keyboardType = KeyboardType.Text)

    var fieldValue by mutableStateOf("")
        private set


    fun onValueChange(string: String) {
        if (isInit) onFieldChange()
        fieldValue = string
        shouldShowLabel = string.isBlank()
    }

    fun onInit(string: String) {

        val str = if (string == "null") "" else string
        if (!isInit) {
            onValueChange(str)
            isInit = true
        }
    }

    private fun matchesWebLink(): Boolean {
        return fieldValue.contains(".com") || fieldValue.contains(".ru")
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