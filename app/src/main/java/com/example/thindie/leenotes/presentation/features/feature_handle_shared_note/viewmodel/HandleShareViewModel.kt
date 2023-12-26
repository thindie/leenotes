package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.entities.Cost
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.domain.usecase.HandleShareUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class HandleShareViewModel @Inject constructor(private val handleShareUseCase: HandleShareUseCase) :
    ViewModel() {


    var isFinished by mutableStateOf(false)
        private set
    private var currentTitle by mutableStateOf("")


    private var currentCost: Int? by mutableStateOf(null)


    var currentDescription by mutableStateOf("")
        private set

    fun onEvent(handleShareViewModelEvent: HandleShareViewModelEvent) {
        when (handleShareViewModelEvent) {
            HandleShareViewModelEvent.Cancel -> {
                isFinished = true
            }

            is HandleShareViewModelEvent.Submit -> {
                currentCost = handleShareViewModelEvent.cost
                currentTitle = handleShareViewModelEvent.description
                onSubmit()
            }

            is HandleShareViewModelEvent.Initial -> {
                 currentDescription = handleShareViewModelEvent.intentBody
            }
        }
    }

    private fun onSubmit(){
        viewModelScope.launch {
          val note = Note(
                id = 0,
                title = currentTitle,
                description = currentDescription,
                creationTimeInMillis = System.currentTimeMillis(),
                cost = if (currentCost != null) Cost(id = 0, price = currentCost!!.toDouble()) else null,
                bindings = null
            )
            handleShareUseCase.invoke(note)
            isFinished = true
        }
    }

}