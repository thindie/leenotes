package com.example.thindie.leenotes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thindie.leenotes.domain.DateFilterHelper
import com.example.thindie.leenotes.domain.Note
import com.example.thindie.leenotes.ui.screens.NoteAppState
import com.example.thindie.leenotes.ui.screens.rememberAppState
import com.example.thindie.leenotes.ui.theme.LeenotesTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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

 