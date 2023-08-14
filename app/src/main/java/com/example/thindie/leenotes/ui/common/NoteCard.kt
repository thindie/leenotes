package com.example.thindie.leenotes.ui.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo
import kotlinx.coroutines.CoroutineScope

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    state: NoteCardState,
    title: String,
    time: String,
    body: String,
) {
    Card(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 3.dp, top = 3.dp)
            .height(state.height.value)
            .fillMaxWidth(),
        shape = RoundedCornerShape(52.dp),
        colors = state.cardColors
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .height(state.normalSize)
        ) {
            LazyRow(Modifier.width(250.dp)) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp),
                        text = title,
                        style = typo.headlineSmall,
                        color = colors.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f, true))
            Text(
                text = time,
                modifier = Modifier.padding(end = 5.dp),
                style = typo.bodySmall,
                color = colors.onSecondaryContainer
            )
            IconButton(
                onClick = state::onTouchExpand,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(
                    painter = state.expandToggler,
                    contentDescription = "",
                    tint = colors.onPrimary
                )
            }
        }
        if (state.isExpanded.value) {
            Column(
                modifier = Modifier.padding(
                    top = 22.dp,
                    start = 18.dp,
                    bottom = 20.dp,
                    end = 18.dp
                ),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(state.expandedSize - state.normalSize * 2)
                ) {
                    item {
                        Text(
                            text = body,
                            style = typo.bodyLarge,
                            color = colors.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f, true))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NotesButton(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .height(state.normalSize / 2)
                            .width(state.normalSize * 2),
                        title = R.string.button_label_remove,
                        isOutlined = true
                    ) {

                    }
                    NotesButton(
                        modifier = Modifier
                            .padding(end = 18.dp)
                            .height(state.normalSize / 2)
                            .width(state.normalSize * 2),
                        title = R.string.button_label_details,
                        isOutlined = true
                    ) {

                    }
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    LeenotesTheme {
        val state = rememberNoteCardState()
        NoteCard(state = state, title = "title", time = "09 aвг 23", body = "some text")
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreviewDark() {
    LeenotesTheme(darkTheme = true) {
        val state = rememberNoteCardState()
        NoteCard(state = state, title = "title", time = "09 aвг 23", body = "some text")
    }
}


@Composable
fun rememberNoteCardState(): NoteCardState {
    val scope = rememberCoroutineScope()
    return remember() {
        NoteCardState(scope)
    }
}

@Stable
class NoteCardState(private val scope: CoroutineScope) {
    val isExpanded = mutableStateOf(false)
    val expandedSize = 350.dp
    val normalSize = 80.dp
    val expandToggler: Painter
        @Composable get() = painterResource(
            id = if (isExpanded.value)
                R.drawable.chevron_up else R.drawable.chevron_down
        )

    val height
        @Composable get() = animateDpAsState(
            targetValue = if (isExpanded.value) expandedSize
            else normalSize, label = "", animationSpec = spring()
        )

    fun onTouchExpand() {
        isExpanded.value = !isExpanded.value
    }

    val cardColors
        @Composable get() = CardDefaults.cardColors(
            containerColor = colors.secondary,

            )
}

