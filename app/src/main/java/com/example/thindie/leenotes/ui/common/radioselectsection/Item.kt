package com.example.thindie.leenotes.ui.common.radioselectsection

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.typo

@Composable
fun <T> NotesRadioItem(
    modifier: Modifier = Modifier,
    t: T,
    isSelected: Boolean,
    @DrawableRes activeState: Int,
    @DrawableRes inActiveState: Int,
    title: @Composable (T) -> String,
    onClick: (T) -> Unit,
) {
    OutlinedIconButton(
        modifier = modifier
            .width(140.dp)
            .padding(vertical = 2.dp, horizontal = 4.dp),
        onClick = { onClick(t) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.wrapContentWidth()
        ) {
            Icon(
                modifier = modifier.padding(horizontal = 2.dp),
                painter = painterResource(
                    id = if (isSelected) activeState
                    else inActiveState
                ),
                contentDescription = ""
            )
            Text(
                modifier = modifier.padding(horizontal = 2.dp),
                text = title.invoke(t),
                style = typo.bodySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesRadioItemPreview() {
    LeenotesTheme {
        NotesRadioItem(
            t = "startup",
            isSelected = true,
            title = { it.toString() },
            onClick = {

            },
            activeState = R.drawable.icon_radio_active,
            inActiveState = R.drawable.icon_radio_unactive
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotesRadioItemPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}