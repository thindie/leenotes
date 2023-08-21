package com.example.thindie.leenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesAppDataBase : RoomDatabase() {
    abstract fun getInstance(): NotesDao
}