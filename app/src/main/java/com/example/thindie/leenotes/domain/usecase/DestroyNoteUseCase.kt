package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NotesRepository
import javax.inject.Inject

class DestroyNoteUseCase @Inject constructor(private val repository: NotesRepository)  {
     suspend operator fun invoke (id: Int) = repository.deleteNote(id)
}