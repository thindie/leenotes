package com.example.thindie.leenotes.ui.screens.detailpaidcostscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CostScreenViewModel @Inject constructor(private val costManager: CostManager) : ViewModel() {
    private val _costState = MutableStateFlow(CostScreenState())
    val costState = _costState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        CostScreenState()
    )

    fun onOpenCostScreen() {
        viewModelScope.launch {
            val costs = costManager.getCosts()
            _costState.value = CostScreenState(costs)
        }
    }

    data class CostScreenState(val costList: List<Cost> = emptyList())
}