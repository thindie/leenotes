package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NoteProvider
import com.example.thindie.leenotes.domain.NotesRepository
import com.example.thindie.leenotes.domain.entities.Note
import javax.inject.Inject

class UpdateNoteUseCase@Inject constructor(private val provider: NoteProvider)  {
     suspend operator fun invoke (note: Note) = provider.updateNote(note)
}