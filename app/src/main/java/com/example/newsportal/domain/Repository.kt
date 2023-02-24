package com.example.newsportal.domain

import com.example.newsportal.domain.models.NewsArticleData

interface Repository {
    suspend fun getNews(): List<NewsArticleData>
    fun setToken(token: String)
}