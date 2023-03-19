package com.example.domain

import com.example.domain.models.NewsData

interface Repository {
    suspend fun getNews(query: String, isConnected: Boolean): List<NewsData>

    fun setToken(token: String)
}