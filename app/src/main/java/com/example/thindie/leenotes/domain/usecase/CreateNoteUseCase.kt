package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NotesRepository
import javax.inject.Inject

class CreateNoteUseCase@Inject constructor(private val repository: NotesRepository)  {
     suspend operator fun invoke (initialedString: String) = repository.addNote(initialedString)
}