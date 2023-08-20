package com.example.thindie.leenotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM $notesTable SORT ")
    fun observeNotes(): Flow<List<NotesEntity>>

    @Upsert
    fun upsertNote(notesEntity: NotesEntity)

    @Query("SELECT * FROM $notesTable WHERE timestamp = :id LIMIT 1")
    fun getNote(id: Long)

    @Delete
    fun deleteNote(notesEntity: NotesEntity)

}
