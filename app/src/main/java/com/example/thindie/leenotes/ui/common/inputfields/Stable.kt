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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
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

@Composable
fun rememberDigitInputState(
    isSingleLine: Boolean,
    @DrawableRes leadingIcon: Int = 0,
    @StringRes hint: Int = 0,
    @StringRes supportingText: Int = 0,
): DigitInputState {
    return remember(isSingleLine) {
        DigitInputState(isSingleLine, leadingIcon, hint, supportingText)
    }
}


@Stable
open class NotesInputFieldState(
    val isSingleLine: Boolean,
    @DrawableRes private val leadingIcon: Int,
    @StringRes private val hint: Int,
    @StringRes private val supportingText: Int,
    val fullSizeField: Float = 1f,
    val halvedSizeField: Float = 0.6f,
) {

    private val shouldDeactivateFocus = mutableStateOf(false)
    private val fieldWidthState =
        mutableStateOf(if (shouldDeactivateFocus.value) halvedSizeField else fullSizeField)

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

    protected val _isError = mutableStateOf(false)

    val height = if (isSingleLine) 75.dp else 130.dp
    val isError: State<Boolean>
        get() = _isError

    protected val _fieldValue = mutableStateOf("")
    val fieldState: State<String>
        get() = _fieldValue

    fun onValueChange(value: String) {
        _isError.value = false
        _fieldValue.value = value
    }

    fun clearField(){
        onValueChange("")
    }

    private fun onWidthLess() {
        fieldWidthState.value = halvedSizeField
        shouldDeactivateFocus.value = false
    }

    fun onWidthRestore() {
        fieldWidthState.value = fullSizeField
        _fieldValue.value = ""
        shouldDeactivateFocus.value = true
    }


    fun onFocusChanged(it: FocusState) {
        if (!it.hasFocus) {
            onWidthRestore()
        } else onWidthLess()
    }
}

@Stable
class DigitInputState(
    private val isLineSingle: Boolean,
    @DrawableRes private val leadingIcon: Int,
    @StringRes private val hint: Int,
    @StringRes private val supportingText: Int,
    fullSize: Float = 1f,
    halvedSize: Float = 0.6f,
) : NotesInputFieldState(
    isSingleLine = isLineSingle,
    leadingIcon = leadingIcon,
    hint = hint,
    supportingText = supportingText,
    fullSizeField = fullSize,
    halvedSizeField = halvedSize
) {

    fun validate(): Boolean  {
        _isError.value =  _fieldValue.value.isDigitsOnly().not()
        return isError.value
    }

}

