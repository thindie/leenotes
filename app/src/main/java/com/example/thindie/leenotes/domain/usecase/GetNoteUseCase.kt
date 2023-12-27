package com.example.thindie.leenotes.domain.usecase

import com.example.thindie.leenotes.domain.NoteProvider
import com.example.thindie.leenotes.domain.entities.Note
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val noteProvider: NoteProvider) {
    suspend operator fun invoke(id: Int): Note {
        return noteProvider.getNoteById(id)
    }

}