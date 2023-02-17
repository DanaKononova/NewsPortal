package com.example.newsportal.data.mappers

import com.example.newsportal.data.model.NewsResponse
import com.example.newsportal.domain.models.NewsArticleData
import com.example.newsportal.domain.models.NewsData
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    operator fun invoke(newsResponse: NewsResponse): NewsData {
        return NewsData(
            articles = (newsResponse.article ?: listOf()) as List<NewsArticleData>
        )
    }
}