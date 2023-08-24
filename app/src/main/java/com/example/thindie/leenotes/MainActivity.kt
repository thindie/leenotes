package com.example.thindie.leenotes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
                NoteAppState(appState = rememberAppState()) { link ->
                    if (link.isNotBlank()) {
                        startActivity(openWebPage(link))
                    }
                }
            }
        }
    }

    private fun openWebPage(url: String): Intent {
        val webpage: Uri = Uri.parse(url)
        return Intent(Intent.ACTION_VIEW, webpage)
    }
}

 