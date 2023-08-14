package com.example.thindie.leenotes.ui.common.inputfields

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.ui.theme.colors

@Composable
fun rememberInputState(
    isSingleLine: Boolean,
    @DrawableRes leadingIcon: Int = 0,
    @StringRes hint: Int = 0,
    @StringRes supportingText: Int = 0,
): NotesInputFieldState {
    return remember(isSingleLine) {
        NotesInputFieldState(isSingleLine, leadingIcon, hint, supportingText)
    }
}

@Stable
class NotesInputFieldState(
    val isSingleLine: Boolean,
    @DrawableRes private val leadingIcon: Int,
    @StringRes private val hint: Int,
    @StringRes private val supportingText: Int,
) {
    private val fieldWidthState = mutableStateOf(1f)

    val width
        @Composable get() = animateFloatAsState(
            targetValue = fieldWidthState.value,
            animationSpec = tween(),
            label = ""
        )
    val textFieldColors
        @Composable get() = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colors.onTertiary,
            unfocusedTextColor = colors.onTertiary,
            disabledTextColor = colors.onTertiary,
            errorTextColor = colors.onTertiary,
            focusedContainerColor = colors.secondary,
            unfocusedContainerColor = colors.secondary,
            disabledContainerColor = colors.secondary,
            errorContainerColor = colors.secondary,
            cursorColor = colors.onTertiary,
            errorCursorColor = colors.onTertiary,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedLeadingIconColor = colors.onSecondaryContainer,
            unfocusedLeadingIconColor = colors.onSecondaryContainer,
            disabledLeadingIconColor = colors.onSecondaryContainer,
            errorLeadingIconColor = colors.onSecondaryContainer,
            focusedTrailingIconColor = colors.onSecondaryContainer,
            unfocusedTrailingIconColor = colors.onSecondaryContainer,
            disabledTrailingIconColor = colors.onSecondaryContainer,
            errorTrailingIconColor = colors.onSecondaryContainer,
            focusedLabelColor = colors.onPrimaryContainer,
            unfocusedLabelColor = colors.onPrimaryContainer,
            disabledLabelColor = colors.onPrimaryContainer,
            errorLabelColor = colors.onPrimaryContainer,

            focusedSupportingTextColor = colors.onPrimaryContainer,
            unfocusedSupportingTextColor = colors.onPrimaryContainer,
            disabledSupportingTextColor = colors.onPrimaryContainer,
            errorSupportingTextColor = colors.onPrimaryContainer,

            )
    val fieldHint
        @Composable get() = if (hint != 0) stringResource(id = hint) else ""
    val fieldSupportingText
        @Composable get() = if (supportingText != 0) stringResource(id = supportingText) else ""

    val Icon
        @Composable get() =
            if (leadingIcon != 0) {
                Icon(painter = painterResource(id = leadingIcon), contentDescription = "")
            } else null

    private val _isError = mutableStateOf(false)

    val height = if (isSingleLine) 86.dp else 154.dp
    val isError: State<Boolean>
        get() = _isError

    private val _fieldValue = mutableStateOf("")
    val fieldState: State<String>
        get() = _fieldValue

    fun onValueChange(value: String) {
        _isError.value = false
        _fieldValue.value = value
    }

    fun onWidthLess(){
        fieldWidthState.value = 0.6f
    }

    fun onWidthRestore(){
        fieldWidthState.value = 1f
    }

    fun isValid(): Boolean {
        return if (isSingleLine) {
            if (_fieldValue.value.isBlank()) {
                _isError.value = true
            }
            _fieldValue.value.isBlank()
        } else true
    }


}