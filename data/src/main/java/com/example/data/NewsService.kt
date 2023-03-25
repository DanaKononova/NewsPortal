package com.example.data

import com.example.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("x-api-key: 273f20ec5b99445fb433eed37faf3eb5")
    @GET("everything")
    fun getNews(
        //= "273f20ec5b99445fb433eed37faf3eb5"
        @Query("q") str: String,
        @Query("sortBy") sort: String = "popularity"
    ): Call<NewsResponse>
}