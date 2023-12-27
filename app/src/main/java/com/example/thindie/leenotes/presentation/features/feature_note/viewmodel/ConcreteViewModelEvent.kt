package com.example.thindie.leenotes.presentation.features.feature_note.viewmodel

sealed class ConcreteViewModelEvent {
    data class ID(val id: Int) : ConcreteViewModelEvent()
    data object Edit : ConcreteViewModelEvent()

    data object DeleteCurrent : ConcreteViewModelEvent()

    data class Save(
        val title: String,
        val description: String,
        val cost: String,
        val bindings: String,
    ) : ConcreteViewModelEvent()
}