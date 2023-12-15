package com.example.thindie.leenotes.presentation.features.feature_notes.allnotesscreen

import androidx.lifecycle.ViewModel
import com.example.thindie.leenotes.data.database.BindingsDao
import com.example.thindie.leenotes.presentation.features.feature_notes.di.NoteFeatureScope
import javax.inject.Inject

@NoteFeatureScope
class NotesScreenViewModel @Inject constructor(val dao: BindingsDao) : ViewModel() {
    init {
        dao
    }
}
