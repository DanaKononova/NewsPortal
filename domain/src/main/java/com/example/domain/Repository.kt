package com.example.domain

import com.example.domain.models.NewsData
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNews(query: String, isConnected: Boolean): Flow<List<NewsData>>

    suspend fun searchNews(query: String, isConnected: Boolean): List<NewsData>

    fun setToken(token: String)
}