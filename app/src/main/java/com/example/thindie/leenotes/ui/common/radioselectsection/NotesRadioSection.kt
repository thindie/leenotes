package com.example.thindie.leenotes.ui.common.radioselectsection

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme

@Composable
fun <T> NotesRadioSection(
    modifier: Modifier = Modifier,
    list: List<T>,
    @DrawableRes activeStatePic: Int = R.drawable.icon_radio_active,
    @DrawableRes unActiveStatePic: Int = R.drawable.icon_radio_unactive,
    isActive: (T) -> Boolean,
    title: @Composable (T) -> String,
    onClick: (T) -> Unit,
) {
    LazyRow(modifier = modifier.padding(vertical = 4.dp, horizontal = 4.dp)) {
        items(list) {
            NotesRadioItem(
                modifier = modifier,
                t = it,
                isSelected = isActive.invoke(it),
                activeState = activeStatePic,
                inActiveState = unActiveStatePic,
                title = { title.invoke(it) },
                onClick = { onClick(it) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesRadioSectionPreview() {
    LeenotesTheme {

    }
}

@Preview(showBackground = true)
@Composable
fun NotesRadioSectionPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}