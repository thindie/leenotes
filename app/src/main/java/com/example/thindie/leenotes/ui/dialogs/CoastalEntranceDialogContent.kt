package com.example.thindie.leenotes.ui.dialogs

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.common.NotesButton
import com.example.thindie.leenotes.ui.common.inputfields.DigitInputState
import com.example.thindie.leenotes.ui.common.inputfields.NotesInputFields
import com.example.thindie.leenotes.ui.common.inputfields.rememberDigitInputState
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo

@Suppress("LongParameterList")
@Composable
fun CoastalEntranceDialogContent(
    modifier: Modifier = Modifier,
    @StringRes dialogTitle: Int,
    @StringRes dismissButtonTitle: Int,
    @StringRes confirmButtonTitle: Int,
    @StringRes currency: Int,
    currencyInputField: DigitInputState = rememberDigitInputState(isSingleLine = true),
    onClickDismiss: () -> Unit,
    onClickConfirm: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(horizontal = 9.dp, vertical = 17.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = dialogTitle),
            style = typo.headlineSmall,
            color = colors.onPrimary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NotesInputFields(
                modifier = Modifier
                    .onFocusChanged {
                        currencyInputField.onFocusChanged(it)
                    }, state = currencyInputField
            )
            if (currencyInputField.width.value == currencyInputField.halvedSizeField) {
                Text(
                    modifier = modifier
                        .clickable {
                            focusManager.clearFocus();
                            currencyInputField.onWidthRestore()
                        },
                    text = stringResource(id = currency),
                    style = typo.labelLarge,
                    color = colors.primaryContainer
                )
            }
        }
        NotesButton(title = dismissButtonTitle, isOutlined = true) {
            onClickDismiss()
        }
        NotesButton(title = confirmButtonTitle, isOutlined = false) {
            currencyInputField.validate()
            if (!currencyInputField.isError.value) {
                onClickConfirm(currencyInputField.fieldState.value)
            } else Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show()
        }
     }
}


@Preview(showBackground = true)
@Composable
fun CoastalEntranceDialogContentPreview() {
    LeenotesTheme {
        NotesDialog(onDismissRequest = {}) {
            CoastalEntranceDialogContent(
                dialogTitle = R.string.text_field_will_spend,
                dismissButtonTitle = R.string.button_label_oh_no,
                confirmButtonTitle = R.string.button_label_dokay,
                currency = R.string.text_field_rubles,
                onClickDismiss = { },
                onClickConfirm = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoastalEntranceDialogContentPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        NotesDialog(onDismissRequest = {}) {
            CoastalEntranceDialogContent(
                dialogTitle = R.string.text_field_will_spend,
                dismissButtonTitle = R.string.button_label_oh_no,
                confirmButtonTitle = R.string.button_label_dokay,
                currency = R.string.text_field_rubles,
                onClickDismiss = { },
                onClickConfirm = { }
            )
        }
    }
}