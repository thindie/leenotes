package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Note
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase@Inject constructor(private val repository: NotesRepository)  {
     operator fun invoke (): Flow<List<Note>> = repository.observeNotes()
}