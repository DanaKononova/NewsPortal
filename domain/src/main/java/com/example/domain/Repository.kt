package com.example.domain

import com.example.domain.models.NewsData
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNews(query: String): Flow<List<NewsData>>

    suspend fun searchNews(query: String): List<NewsData>

    fun setToken(token: String)
}