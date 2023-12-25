package com.example.thindie.leenotes.common.design_system.inputfields

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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

@Composable
fun rememberInputState(
    isSingleLine: Boolean,
    @DrawableRes leadingIcon: Int = 0,
    @StringRes hint: Int = 0,
    @StringRes supportingText: Int = 0,
    initialValue: String = "",
): NotesInputFieldState {
    return remember(isSingleLine) {
        NotesInputFieldState(isSingleLine, leadingIcon, hint, supportingText, initialValue)
    }
}

@Composable
fun rememberDigitInputState(
    isSingleLine: Boolean,
    @DrawableRes leadingIcon: Int = 0,
    @StringRes hint: Int = 0,
    @StringRes supportingText: Int = 0,
    initialValue: String = "",
): DigitInputState {
    return remember(isSingleLine) {
        DigitInputState(isSingleLine, leadingIcon, hint, supportingText, initialValue)
    }
}


@Stable
open class NotesInputFieldState(
    val isSingleLine: Boolean,
    @DrawableRes private val leadingIcon: Int,
    @StringRes private val hint: Int,
    @StringRes private val supportingText: Int,
    val initialValue: String,
    val fullSizeField: Float = 1f,
    val halvedSizeField: Float = 0.6f,
) {

    private val shouldDeactivateFocus = mutableStateOf(false)
    private val fieldWidthState =
        mutableStateOf(if (shouldDeactivateFocus.value) halvedSizeField else fullSizeField)

    val width
        @Composable get() = animateFloatAsState(
            targetValue = fieldWidthState.value, animationSpec = tween(), label = ""
        )
    val textFieldColors
        @Composable get() = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
            disabledTextColor = MaterialTheme.colorScheme.onTertiary,
            errorTextColor = MaterialTheme.colorScheme.onTertiary,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            errorContainerColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.onTertiary,
            errorCursorColor = MaterialTheme.colorScheme.onTertiary,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            errorLeadingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            errorTrailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,

            focusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,

            )
    val fieldHint
        @Composable get() = if (hint != 0 && width.value == fullSizeField) Text(
            text = stringResource(
                id = hint
            )
        ) else Text(text = "")
    val fieldSupportingText
        @Composable get() = if (supportingText != 0 && width.value == fullSizeField) Text(
            text = stringResource(
                id = supportingText
            )
        ) else Text(text = "")

    val Icon
        @Composable get() = if (leadingIcon != 0) {
            Icon(painter = painterResource(id = leadingIcon), contentDescription = "")
        } else null

    protected val _isError = mutableStateOf(false)

    val height = if (isSingleLine) 90.dp else 130.dp
    val isError: State<Boolean>
        get() = _isError

    protected val _fieldValue = mutableStateOf(initialValue)
    val fieldState: State<String>
        get() = _fieldValue

    fun onValueChange(value: String) {
        Log.d("SERVICE_TAG", value)
        _isError.value = false
        _fieldValue.value = value
    }

    fun onResetWidthAndState() {
        clearField()
        onWidthRestore()
    }

    fun clearField() {
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
    initialValue: String = "",
    fullSize: Float = 1f,
    halvedSize: Float = 0.6f,
) : NotesInputFieldState(
    isSingleLine = isLineSingle,
    leadingIcon = leadingIcon,
    hint = hint,
    supportingText = supportingText,
    initialValue = initialValue,
    fullSizeField = fullSize,
    halvedSizeField = halvedSize
) {

    fun digitValue(): Int {
        val fieldValue = try {
            _fieldValue.value.toInt()
        } catch (e: Exception) {
            _isError.value = true
            0
        }
        return fieldValue
    }
}

