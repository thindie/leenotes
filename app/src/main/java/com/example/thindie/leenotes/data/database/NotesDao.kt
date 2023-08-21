package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM $notesTable")
    fun observeNotes(): Flow<List<NotesEntity>>

    @Upsert
    suspend fun upsertNote(notesEntity: NotesEntity)

    @Query("SELECT * FROM $notesTable WHERE timestamp = :id LIMIT 1")
    suspend fun getNote(id: Long): NotesEntity

    @Delete
    suspend fun deleteNote(notesEntity: NotesEntity)

}
