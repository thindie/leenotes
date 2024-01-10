package com.example.thindie.leenotes.presentation.features.feature_note.concrete_note

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.thindie.leenotes.QFun
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.presentation.features.feature_note.concrete_note.screen.ConcreteNoteScreen
import com.example.thindie.leenotes.presentation.features.feature_note.di.ConcreteNoteFeatureComponent
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteNoteViewModel
import com.example.thindie.leenotes.presentation.features.feature_note.viewmodel.ConcreteViewModelEvent

private val argumentKey = "noteId"
private val route = "concreteNote/"
val concreteNoteRoute = "$route{$argumentKey}"
fun NavGraphBuilder.concreteNote(onReturnStartScreen: () -> Unit) {
    composable(
        route = concreteNoteRoute,
        arguments = listOf(navArgument(argumentKey) {
            type = NavType.StringType
        })
    ) {
        val argument = it.arguments?.getString(argumentKey)
        val parsedArgument = getArgument(argument ?: "")
        val id = getIdFromArgument(parsedArgument)
        val context = LocalContext.current
        val daggerComponent = initDaggerComponent(context = context)
        if (daggerComponent != null) {
            val factory = daggerComponent.provideFactory()
            val viewModel: ConcreteNoteViewModel = viewModel(factory = factory)
            viewModel.onEvent(ConcreteViewModelEvent.ID(id))
            ConcreteNoteScreen(viewModel = viewModel, onReturnStartScreen = onReturnStartScreen)
        }

    }
}

private fun getIdFromArgument(parsedArg: String?): Int {
    QFun(parsedArg)
    return if (parsedArg == null) -1 else {
        try {
            parsedArg.toInt()
        } catch (_: NumberFormatException) {
            -1
        }
    }
}

private fun getArgument(rawArg: String) = rawArg.substringAfterLast("}", "")


@Composable
private fun initDaggerComponent(context: Context): ConcreteNoteFeatureComponent? {
    val currentApp = context as? App
    return if (currentApp != null) {
        ConcreteNoteFeatureComponent.init(currentApp.getAppComponent())
    } else null
}