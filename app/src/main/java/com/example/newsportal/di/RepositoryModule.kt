package com.example.newsportal.di

import com.example.newsportal.data.RepositoryImpl
import com.example.newsportal.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getRepository(impl: RepositoryImpl): Repository
}