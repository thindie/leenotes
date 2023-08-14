package com.example.thindie.leenotes.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo


@Composable
fun Qwerty(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = typo.displayLarge,
        color = colors.primary
    )

    Text(
        text = stringResource(id = R.string.app_name),
        style = typo.displayMedium,
        color = colors.secondary
    )

    Text(
        text = stringResource(id = R.string.app_name),
        style = typo.displaySmall,
        color = colors.tertiary
    )

}


@Preview(showBackground = true)
@Composable
fun QwertyPreview() {
    Column {
        LeenotesTheme {
            Qwerty()
        }
        LeenotesTheme(darkTheme = true) {
            Qwerty()
        }
    }

}