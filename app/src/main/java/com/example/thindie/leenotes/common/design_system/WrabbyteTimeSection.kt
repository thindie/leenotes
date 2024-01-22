package com.example.thindie.leenotes.common.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WrabbyteTimeSection(modifier: Modifier = Modifier, currentTime: String) {

    Row(
        modifier = modifier
            .padding()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = currentTime,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            style = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold, color = LocalContentColor.current.copy(0.4f)
            )
        )
    }
}