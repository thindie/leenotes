package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM $notesTable")
    fun observeNotes(): Flow<List<NoteDbModel>>

    @Query("SELECT * FROM $notesTable")
    suspend fun getNotes(): List<NoteDbModel>

    @Upsert
    suspend fun upsertNote(noteDbModel: NoteDbModel)

    @Query("SELECT * FROM $notesTable WHERE timestamp = :id LIMIT 1")
    suspend fun getNote(id: Long): NoteDbModel

    @Delete
    suspend fun deleteNote(noteDbModel: NoteDbModel)

}
