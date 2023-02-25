package com.example.newsportal.domain

import com.example.newsportal.data.dataBase.NewsEntity

interface Repository {
    suspend fun getNews(isConnected: Boolean): List<NewsEntity>

    fun setToken(token: String)

    suspend fun getAll(): List<NewsEntity>

    suspend fun insertAll(news: List<NewsEntity>)

    suspend fun delete(news: List<NewsEntity>)
}