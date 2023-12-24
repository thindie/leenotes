package com.example.thindie.leenotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelFactory
import com.example.thindie.leenotes.presentation.common.theme.LeenotesTheme
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.HandleIntentScreen
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleFeatureComponent
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel
import javax.inject.Inject

class HandleShareIntentActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: HandleShareViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val invokedIntent = handleIntent(intent = intent)
        if (invokedIntent != null) {
            onValidIntent(invokedIntent)
            renderScreen()
        }
    }

    private fun onValidIntent(intent: Intent) {
        dependencyInjection()
        handleSendText(intent)
    }

    @Composable
    fun listenFinish(viewModel: HandleShareViewModel) {
        if (viewModel.isFinished) {
            val activity = LocalContext.current as? HandleShareIntentActivity
            activity?.finish()
        }
    }

    private fun renderScreen() {
        setContent {
            LeenotesTheme {
                listenFinish(viewModel = viewModel)
                HandleIntentScreen(viewModel = viewModel)
            }
        }
    }

    private fun dependencyInjection() {
        val dependenciesProvider = (application as? App)?.getAppComponent()
        initDaggerComponent(dependenciesProvider)
    }


    private fun initDaggerComponent(dependenciesProvider: DependenciesProvider?) {
        if (dependenciesProvider != null) {
            val intentHandleComponent = HandleFeatureComponent.init(dependenciesProvider)
            intentHandleComponent.inject(this)
        }
    }

    private fun handleSendText(intent: Intent?) {
        if (intent != null) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let { viewModel.onHandleRawString(it) }
        }
    }

    private fun handleIntent(intent: Intent?): Intent? {
        return when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    intent
                } else null
            }

            else -> {
                null
            }
        }
    }
}