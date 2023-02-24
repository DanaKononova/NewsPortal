package com.example.newsportal.di

import com.example.newsportal.data.RepositoryImpl
import com.example.newsportal.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getRepository(impl: RepositoryImpl): Repository
}