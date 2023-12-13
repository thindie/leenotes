package com.example.thindie.leenotes.data.di

import com.example.thindie.leenotes.data.database.CostsDao
import com.example.thindie.leenotes.data.database.NotesAppDataBase
import com.example.thindie.leenotes.data.database.NotesDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    fun bindDao(dataBase: NotesAppDataBase): NotesDao {
        return dataBase.getInstance()
    }

    @Provides
    fun provideCostDao(dataBase: NotesAppDataBase): CostsDao {
        return dataBase.getCostsInstance()
    }
}
