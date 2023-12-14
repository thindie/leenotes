package com.example.thindie.leenotes.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.presentation.common.theme.LeenotesTheme
import com.example.thindie.leenotes.presentation.common.theme.seedColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NotesTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.app_name,
    action: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp, horizontal = 17.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.displaySmall,
            color = seedColor
        )
        IconButton(onClick = action) {
            Icon(
                painter = painterResource(id = R.drawable.icon_menu),
                contentDescription = "",
                tint = seedColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesTopAppBarPreview() {
    LeenotesTheme {
        NotesTopAppBar(action = {})
    }
}
