package com.example.newsportal.data

import com.example.newsportal.data.mappers.NewsArticleMapper
import com.example.newsportal.di.SourceModule
import com.example.newsportal.domain.models.NewsArticleData
import com.example.newsportal.domain.Repository
import com.example.newsportal.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsArticleMapper,
    private val netService: UserDataSource
) : Repository {
    override suspend fun getNews(): List<NewsArticleData> {
        return withContext(Dispatchers.IO) {
            val obj = service.getNews(netService.getUserToken()).execute().body() ?: throw Exception()
            obj.article?.map { mapper.invoke(it) } ?: listOf()
        }
    }

    override fun setToken(token: String) {
        netService.setUserToken(token)
    }

}