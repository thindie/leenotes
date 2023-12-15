package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.common.di.dispatchers.IODispatcher
import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NoteFeatureScope
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@NoteFeatureScope
class NotesScreenViewModel @Inject
constructor(
    val dao: BindingsDao,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    init {
        dao
        viewModelScope.launch {
            withContext(ioDispatcher){}
        }

    }
}
