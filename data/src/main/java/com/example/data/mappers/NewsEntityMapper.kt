package com.example.data.mappers

import com.example.data.dataBase.NewsEntity
import com.example.domain.models.NewsData
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