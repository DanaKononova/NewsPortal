package com.example.newsportal.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val article: List<NewsArticleResponse>? = null
)