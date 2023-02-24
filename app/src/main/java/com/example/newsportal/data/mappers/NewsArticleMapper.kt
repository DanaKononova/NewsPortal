package com.example.newsportal.data.mappers

import com.example.newsportal.data.model.NewsArticleResponse
import com.example.newsportal.domain.models.NewsArticleData
import javax.inject.Inject

class NewsArticleMapper @Inject constructor() {
    operator fun invoke(newsArticleResponse: NewsArticleResponse): NewsArticleData {
        return NewsArticleData(
            title = newsArticleResponse.title ?: "",
            newsUrl = newsArticleResponse.newsUrl ?: "",
            urlToImage = newsArticleResponse.urlToImage ?: "",
            description = newsArticleResponse.description ?: ""
        )
    }
}