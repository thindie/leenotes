package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel

sealed class HandleShareViewModelEvent  {
    data object Cancel: HandleShareViewModelEvent()
    data class Submit(val description: String, val cost: Int? = null): HandleShareViewModelEvent()

    data class Initial(val intentBody: String): HandleShareViewModelEvent()
}