package com.example.data.model

import com.squareup.moshi.Json

data class NewsArticleResponse(
    @Json(name = "url") val newsUrl: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "urlToImage") val urlToImage: String? = null,
    @Json(name = "description") val description: String? = null
)