package com.example.newsportal.di

import android.content.Context
import androidx.room.Room
import com.example.data.dataBase.NewsDao
import com.example.data.dataBase.NewsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, "NewsList")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: NewsDataBase): NewsDao = db.userDao()
}