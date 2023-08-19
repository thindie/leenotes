package com.example.thindie.leenotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _id = MutableStateFlow(0L)
    val idState = _id.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = 0L
    )

    fun onClickConcreteNote(id: Long) {
        _id.value = id
    }
}