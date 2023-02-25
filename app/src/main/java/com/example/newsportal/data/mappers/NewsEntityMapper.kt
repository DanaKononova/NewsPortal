package com.example.newsportal.data.mappers

import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.data.model.NewsArticleResponse
import com.example.newsportal.domain.models.NewsData
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() {
    operator fun invoke(newsEntity: NewsEntity) = with(newsEntity) {
        NewsData(
            title = newsEntity.title,
            newsUrl = newsEntity.newsUrl,
            urlToImage = newsEntity.urlToImage,
            description = newsEntity.description
        )
    }
}