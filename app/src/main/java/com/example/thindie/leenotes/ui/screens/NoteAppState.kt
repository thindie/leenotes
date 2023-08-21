package com.example.thindie.leenotes.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.thindie.leenotes.MainViewModel
import com.example.thindie.leenotes.R
import com.example.thindie.leenotes.ui.common.NoteBottomSheet
import com.example.thindie.leenotes.ui.common.NoteBottomSheetState
import com.example.thindie.leenotes.ui.common.rememberNoteBottomSheetState
import com.example.thindie.leenotes.ui.screens.allnotesscreen.allNotesScreen
import com.example.thindie.leenotes.ui.screens.allnotesscreen.routeAllNotes
import com.example.thindie.leenotes.ui.screens.concretenotescreen.concreteNote
import com.example.thindie.leenotes.ui.theme.LeenotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppState(
    viewModel: MainViewModel = hiltViewModel(),
    bottomSheetState: NoteBottomSheetState = rememberNoteBottomSheetState(),
    appState: NoteAppState,
) {
    val idState = viewModel.idState.collectAsState()

    val spentState =
        viewModel.summarySumCostNotes
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    NavHost(
        navController = appState.navHostController,
        startDestination = appState.currentDestination.value?.route ?: routeAllNotes,
    ) {
        allNotesScreen(onClickConcreteNote = {
            viewModel.onClickConcreteNote(it)
            appState.navigateDetailScreen()
        }, onClickMenu = { viewModel.onExpandMenu(); bottomSheetState.showList() })
        concreteNote(id = { idState.value }, onClickBack = {
            appState.navigateHomeScreen()
        }, onClickMenu = { viewModel.onExpandMenu(); bottomSheetState.showList() })
    }
    NoteBottomSheet(
        title = R.string.text_field_summary,
        alreadySpent = R.string.text_field_arleady_spend,
        willSpend = R.string.text_field_will_spend,
        state = bottomSheetState,
        sumNotes = spentState.value.sumNotes,
        sumCosts = spentState.value.sumCosts
    )
}


@Preview(showBackground = true)
@Composable
fun NoteAppStatePreview() {
    LeenotesTheme {

    }
}

@Preview(showBackground = true)
@Composable
fun NoteAppStatePreviewDark() {
    LeenotesTheme(darkTheme = true) {

    }
}

@Composable
fun rememberAppState(navHostController: NavHostController = rememberNavController()): NoteAppState {
    return remember {
        NoteAppState(navHostController)
    }
}

@Stable
class NoteAppState(val navHostController: NavHostController) {

    private val _currentDestination = mutableStateOf(
        navHostController.currentDestination ?: navHostController.findDestination(routeAllNotes)
    )
    val currentDestination: State<NavDestination?>
        get() = _currentDestination

    fun navigateHomeScreen() {
        navigate(routeAllNotes)
    }

    fun navigateDetailScreen() {
        navigate(concreteNote)
    }

    private fun navigate(route: String) {
        navHostController
            .navigate(route = route) {
                launchSingleTop = true
                restoreState = true
            }
    }
}