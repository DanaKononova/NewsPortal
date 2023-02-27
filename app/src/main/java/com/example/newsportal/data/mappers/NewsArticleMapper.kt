package com.example.newsportal.data.mappers

import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.data.model.NewsArticleResponse
import javax.inject.Inject

class NewsArticleMapper @Inject constructor() {
    operator fun invoke(newsArticleResponse: NewsArticleResponse) = with(newsArticleResponse) {
        NewsEntity(
            title = newsArticleResponse.title ?: "",
            newsUrl = newsArticleResponse.newsUrl ?: "",
            urlToImage = newsArticleResponse.urlToImage ?: "",
            description = newsArticleResponse.description ?: ""
        )
    }
}