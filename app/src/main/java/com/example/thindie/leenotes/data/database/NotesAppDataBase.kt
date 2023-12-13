package com.example.thindie.leenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import javax.inject.Singleton

@Singleton
@Database(entities = [NoteDbModel::class, CostDbModel::class], version = 1, exportSchema = false)
abstract class NotesAppDataBase : RoomDatabase() {
    abstract fun getInstance(): NotesDao
    abstract fun getCostsInstance(): CostsDao
}
