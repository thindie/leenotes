package com.example.thindie.leenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thindie.leenotes.ui.screens.NoteAppState
import com.example.thindie.leenotes.ui.screens.rememberAppState
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeenotesTheme {
                NoteAppState(appState = rememberAppState())
            }
        }
    }
}

 