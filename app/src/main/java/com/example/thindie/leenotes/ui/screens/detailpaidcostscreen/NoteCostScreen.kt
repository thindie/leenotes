package com.example.thindie.leenotes.ui.screens.detailpaidcostscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.domain.Cost
import com.example.thindie.leenotes.ui.common.NotesTopAppBar
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import com.example.thindie.leenotes.ui.theme.colors
import com.example.thindie.leenotes.ui.theme.typo

@Composable
fun NoteCostScreen(
    modifier: Modifier = Modifier,
    costScreenViewModel: CostScreenViewModel = hiltViewModel(),
    onClickMenu: () -> Unit,
    onClickBack: () -> Unit,
) {
    costScreenViewModel.onOpenCostScreen()
    val costScreenState =
        costScreenViewModel.costState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    Scaffold(
        modifier = modifier,
        topBar = { NotesTopAppBar(action = { onClickMenu() }) },
        containerColor = colors.primary,
    ) {
        LazyVerticalGrid(
            modifier = modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .padding(it)
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.Center
        ) {
            items(costScreenState.value.costList) {
                NoteCostCard(cost = it)
            }
        }
    }
}


@Composable
fun NoteCostCard(cost: Cost) {
    OutlinedCard(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        border = BorderStroke(Dp.Hairline, color = colors.onSecondary)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyRow() {
                item {
                    Text(text = cost.title, style = typo.titleLarge, color = colors.onPrimary)
                }
            }

            Text(
                text = stringResource(R.string.text_field_cost_rub, cost.cost),
                style = typo.titleSmall,
                color = colors.onTertiary
            )
            Text(text = cost.getDate(), style = typo.bodyMedium, color = colors.onSecondary)

        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteCostScreenPreview() {
    LeenotesTheme {
        NoteCostCard(
            cost = Cost(
                timeStamp = 0,
                title = "ad",
                day = "sad",
                month = "fd",
                year = "asd",
                cost = 21234
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCostScreenPreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}