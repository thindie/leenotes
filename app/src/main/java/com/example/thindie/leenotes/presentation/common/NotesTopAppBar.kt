package com.example.thindie.leenotes.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.common.design_system.IconHolder
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme
import com.example.thindie.leenotes.common.design_system.theme.seedColor
import com.example.thindie.leenotes.presentation.IconsHub

@Composable
fun NotesTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.app_name,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp, horizontal = 17.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter = IconHolder.render(IconsHub.wrabbyte).getIcon(),
            modifier = modifier
                .clickable { onClick() }
                .size(72.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        ClickableText(
            text = AnnotatedString(text = stringResource(id = title)),
            style = MaterialTheme.typography.displaySmall.copy(
                seedColor,
                fontWeight = FontWeight.Bold
            ),
            onClick = { onClick() }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NotesTopAppBarPreview() {
    LeenotesTheme {
        NotesTopAppBar()
    }
}
