package com.example.thindie.leenotes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.thindie.leenotes.common.design_system.TransparentSystemBars
import com.example.thindie.leenotes.common.design_system.theme.LeenotesTheme
import com.example.thindie.leenotes.common.di.App
import com.example.thindie.leenotes.common.di.DependenciesProvider
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelFactory
import com.example.thindie.leenotes.data.timeManagement.TimeOperator
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.composables.HandleIntentScreen
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleFeatureComponent
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModelEvent
import javax.inject.Inject

class HandleShareIntentActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var timeOperatorProvider: TimeOperator

    private val viewModel: HandleShareViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val invokedIntent = handleIntent(intent = intent)
        if (invokedIntent != null) {
            onSuccessValidateIntent(invokedIntent)
            val currentTime = provideCurrentTime(timeOperatorProvider)
            renderScreen(currentTime)
        } else {
            Toast.makeText(
                this,
                getString(R.string.cant_create_note_with_this_thing), Toast.LENGTH_SHORT
            )
                .show()
            this.finish()
        }
    }


    private fun onSuccessValidateIntent(intent: Intent) {
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

    private fun renderScreen(currentTime: String) {
        setContent {
            LeenotesTheme {
                TransparentSystemBars(isInDarkTheme = isSystemInDarkTheme())
                listenFinish(viewModel = viewModel)
                HandleIntentScreen(viewModel = viewModel, currentTime = currentTime)
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
            intent.getStringExtra(Intent.EXTRA_TEXT)
                ?.let { viewModel.onEvent(HandleShareViewModelEvent.Initial(it)) }
        }
    }

    private fun provideCurrentTime(timeOperatorProvider: TimeOperator): String {
        return timeOperatorProvider
            .getCurrent(System.currentTimeMillis())
    }

    private fun handleIntent(intent: Intent?): Intent? {
        return when (intent?.action) {
            Intent.ACTION_SEND -> {
                if (getString(R.string.intent_text_plain) == intent.type) {
                    intent
                } else null
            }

            else -> {
                null
            }
        }
    }
}