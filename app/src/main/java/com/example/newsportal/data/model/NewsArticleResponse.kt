package com.example.newsportal.data.model

import com.google.gson.annotations.SerializedName

data class NewsArticleResponse(
    @SerializedName("url") val newsUrl: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("urlToImage") val urlToImage: String? = null,
    @SerializedName("description") val description: String? = null
)