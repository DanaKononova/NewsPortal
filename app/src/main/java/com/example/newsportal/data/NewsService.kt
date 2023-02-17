package com.example.newsportal.data

import com.example.newsportal.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    fun getNews(
        @Query("q") str: String = "Belarus",
        @Query("sortBy") sort: String = "popularity",
        @Query("apiKey") apiKey: String = "273f20ec5b99445fb433eed37faf3eb5"
    ): Call<NewsResponse>
}