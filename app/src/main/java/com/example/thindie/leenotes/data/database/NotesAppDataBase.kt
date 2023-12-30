package com.example.thindie.leenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thindie.leenotes.data.database.entities.BindingsDbModel
import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.data.database.entities.NoteDbModel
import javax.inject.Singleton

@Singleton
@Database(entities = [NoteDbModel::class, CostDbModel::class, BindingsDbModel::class], version = 3, exportSchema = false)
abstract class NotesAppDataBase : RoomDatabase() {
    abstract fun getInstance(): NotesDao
    abstract fun getCostsInstance(): CostsDao

    abstract fun getBindingsInstance(): BindingsDao
}
