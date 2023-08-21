package com.example.thindie.leenotes.di

import android.app.Application
import androidx.room.Room
import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesAppDataBase
import com.example.thindie.leenotes.data.database.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun bindDao(dataBase: NotesAppDataBase): NotesDao {
        return dataBase.getInstance()
    }

    @Provides
    fun provideCostDao(dataBase: NotesAppDataBase): CostsDao {
        return dataBase.getCostsInstance()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private const val DB_NAME = "notes"

    @Provides
    @Singleton
    fun provideDataBase(application: Application): NotesAppDataBase {
        return Room.databaseBuilder(
            context = application, klass = NotesAppDataBase::class.java, name = DB_NAME
        ).build()
    }
}