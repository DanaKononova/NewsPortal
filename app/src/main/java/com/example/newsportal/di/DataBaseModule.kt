package com.example.newsportal.di

import android.content.Context
import androidx.room.Room
import com.example.newsportal.data.dataBase.NewsDao
import com.example.newsportal.data.dataBase.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, "NewsList")
            .build()
    }

    @Provides
    fun provideUserDao(db: NewsDataBase): NewsDao = db.userDao()
}