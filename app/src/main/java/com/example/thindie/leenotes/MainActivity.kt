package com.example.thindie.leenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.thindie.leenotes.application.di.AppComponent
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.presentation.common.NotesTopAppBar
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.allNotesScreen


class MainActivity : ComponentActivity(), App {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LeenotesTheme {
                Scaffold(topBar = { NotesTopAppBar {} })
                { padding ->
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        navController = rememberNavController(),
                        startDestination = "allNotes"
                    ) {
                        allNotesScreen(onClickMenu = {}, onClickConcreteNote = {})
                    }
                }

            }
        }
    }

    override fun getAppComponent(): AppComponent {
        val application = application
        application as App
        return application.getAppComponent()
    }
}

 