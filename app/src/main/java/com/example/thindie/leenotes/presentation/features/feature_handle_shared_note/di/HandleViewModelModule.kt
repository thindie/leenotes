package com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.common.di.viewmodels_factory.ViewModelKey
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.viewmodel.HandleShareViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HandleViewModelModule {

    @HandleScope
    @ViewModelKey(HandleShareViewModel::class)
    @IntoMap
    @Binds
    fun bindHandleViewModel(shareViewModel: HandleShareViewModel): ViewModel
}