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

    fun onClickHandle() {
        viewModelScope.launch {
            if (currentTitle.isEmpty()) {
                handleShareUseCase(
                    Note(
                        title = currentTitle,
                        id = 0,
                        description = currentDescription,
                        creationTimeInMillis = System.currentTimeMillis(),
                        cost = getCost(),
                        bindings = null,
                    )
                )
            }

        }
    }

    fun onClickCancel() {
        isFinished = true
    }


    fun onHandleRawString(rawString: String) {
        currentDescription = rawString
    }

    fun onSelectCost(cost: Int) {
        currentCost = cost
    }

    fun onProvideTitle(title: String) {
        currentTitle = title
    }

    private fun getCost(): Cost? {
       return if (currentCost == null) null else Cost(
            id = 0, price = currentCost?.toDouble() ?: 0.0
        )
    }
}