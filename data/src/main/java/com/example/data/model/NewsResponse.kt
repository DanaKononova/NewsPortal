package com.example.data.model

import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "articles") val article: List<NewsArticleResponse>? = null
)