package com.example.thindie.leenotes.application.di

import android.content.Context
import com.example.thindie.leenotes.MainActivity
import com.example.thindie.leenotes.data.database.di.LocalSourceComponent
import com.example.thindie.leenotes.data.database.di.LocalSourceProvider
import dagger.Component

@Component(dependencies = [LocalSourceProvider::class])
interface AppComponent {

    companion object{
        fun init(context: Context): AppComponent {
            val localSource = LocalSourceComponent.init(context)
            return DaggerAppComponent
                .factory()
                .create(localSource)
        }
    }
    @Component.Factory
    interface  Factory{
        fun create(localSourceProvider: LocalSourceProvider): AppComponent
    }

    fun inject(activity: MainActivity)
}