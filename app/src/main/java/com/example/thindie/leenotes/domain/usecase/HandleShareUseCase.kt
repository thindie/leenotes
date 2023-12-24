package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Note
import com.example.thindie.leenotes.presentation.features.feature_handle_shared_note.di.HandleScope
import javax.inject.Inject
@HandleScope
class HandleShareUseCase@Inject constructor(private val repository: NotesRepository) {
    suspend operator fun invoke(note: Note){
        repository.updateNote(note)
    }
}