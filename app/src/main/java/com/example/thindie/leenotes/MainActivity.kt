package com.example.thindie.leenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.thindie.leenotes.application.di.AppComponent
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.presentation.common.theme.LeenotesTheme
import com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen.allNotesScreen


class MainActivity : ComponentActivity(), App {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            LeenotesTheme {
                    NavHost(navController = rememberNavController(), startDestination = "allNotes" ){
                        allNotesScreen(onClickMenu = {}, onClickConcreteNote = {})
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

 