package com.example.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val article: List<NewsArticleResponse>? = null
)