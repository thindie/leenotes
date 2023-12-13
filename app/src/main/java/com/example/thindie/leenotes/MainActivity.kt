package com.example.thindie.leenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.thindie.leenotes.application.ComponentCoordinator
import com.example.thindie.leenotes.application.ComponentReceiver
import com.example.thindie.leenotes.application.di.AppComponent
import com.example.thindie.leenotes.data.database.NotesDao
import com.example.thindie.leenotes.presentation.common.theme.LeenotesTheme
import javax.inject.Inject
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity(), ComponentReceiver {
    @Inject
    lateinit var dao: NotesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideDependencies(this)

        setContent {
            LeenotesTheme {

            }
        }
    }

    private fun provideDependencies(activity: MainActivity) {
        requestComponentCoordinator()
            ?.provideAppComponent(activity)
    }

    override fun setAppComponent(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    private fun requestComponentCoordinator(): ComponentCoordinator? {
      return  (application as? ComponentCoordinator)
    }


}

 