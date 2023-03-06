package com.example.newsportal.di

import android.content.Context
import com.example.newsportal.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, DataBaseModule::class, NetworkModule::class, RepositoryModule::class, SourceModule::class]
)
interface AppComponent{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}