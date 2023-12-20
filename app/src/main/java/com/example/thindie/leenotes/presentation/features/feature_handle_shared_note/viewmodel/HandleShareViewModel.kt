package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.domain.usecase.HandleShareUseCase
import javax.inject.Inject

class HandleShareViewModel @Inject constructor(private val handleShareUseCase: HandleShareUseCase) :
    ViewModel()