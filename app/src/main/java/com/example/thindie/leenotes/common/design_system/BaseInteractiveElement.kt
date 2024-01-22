package com.example.thindie.leenotes.common.design_system

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.thindie.leenotes.common.design_system.input_field.InputFieldState
import com.example.thindie.leenotes.presentation.IconsHub

@Composable
fun WrabbyteInteractiveElement(
    inputState: InputFieldState,
    @StringRes title: Int,
    initialValue: String?,
    shape: Shape,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
) {


    ElevatedCard(
        shape = shape, elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 3.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 7.dp,
            draggedElevation = 2.dp,
            disabledElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title), style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        0.7f
                    )
                )
            )
            OutlinedIconButton(
                onClick = { inputState.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(
                        0.7f
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            inputState.onInit(initialValue.orEmpty())
            BasicTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                value = inputState.fieldValue,
                maxLines = 10,
                onValueChange = inputState::onValueChange,
                textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                keyboardOptions = inputState.keyboardOptions
            )
        }
    }
}

@Composable
fun WrabbyteInteractiveElement(
    inputState: InputFieldState,
    @StringRes title: Int,
    initialValue: String?,
    maxTextLines: Int = 3,
    shape: Shape,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    content: @Composable () -> Unit
) {


    ElevatedCard(
        shape = shape, elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 3.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 7.dp,
            draggedElevation = 2.dp,
            disabledElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title), style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        0.7f
                    )
                )
            )
            OutlinedIconButton(
                onClick = { inputState.onValueChange("") }, modifier = Modifier.scale(0.5f)
            ) {
                Icon(
                    painter = IconHolder.render(IconsHub.cancel).getIcon(),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(
                        0.7f
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            inputState.onInit(initialValue.orEmpty())
            BasicTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                value = inputState.fieldValue,
                maxLines = maxTextLines,
                onValueChange = inputState::onValueChange,
                textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                keyboardOptions = inputState.keyboardOptions
            )
        }
        content()
    }
}