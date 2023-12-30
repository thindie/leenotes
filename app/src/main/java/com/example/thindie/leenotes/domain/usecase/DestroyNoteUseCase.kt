package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NoteProvider
import com.example.thindie.leenotes.domain.NotesRepository
import javax.inject.Inject

class DestroyNoteUseCase @Inject constructor(private val provider: NoteProvider)  {
     suspend operator fun invoke (id: Int) = provider.deleteNote(id)
}