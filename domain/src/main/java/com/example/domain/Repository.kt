package com.example.domain

import com.example.domain.models.NewsData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getNews(): Observable<List<NewsData>>

    fun getResponseToDataBase(): Completable

    fun searchNews(query: String): Single<List<NewsData>>

    fun setToken(token: String)
}