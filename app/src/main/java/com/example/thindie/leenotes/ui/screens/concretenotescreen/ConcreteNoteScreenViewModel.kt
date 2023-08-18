package com.example.thindie.leenotes.ui.screens.concretenotescreen

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.domain.FakeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConcreteNoteScreenViewModel @Inject constructor(private val repo: FakeRepo) : ViewModel() {

}