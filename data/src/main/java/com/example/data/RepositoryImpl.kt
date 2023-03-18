package com.example.data

import com.example.data.mappers.NewsArticleMapper
import com.example.data.mappers.NewsEntityMapper
import com.example.domain.Repository
import com.example.domain.models.NewsData
import com.example.newsportal.source.DataBaseSource
import com.example.newsportal.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsArticleMapper,
    private val netService: UserDataSource,
    private val dataBaseSource: DataBaseSource,
    private val entityMapper: NewsEntityMapper
) : Repository {
    override suspend fun getNews(isConnected: Boolean): List<NewsData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val obj =
                    service.getNews(netService.getUserToken()).execute().body() ?: throw Exception()
                val newsList = (obj.article ?: listOf()).map { mapper(it) }
                dataBaseSource.delete(dataBaseSource.getAll())
                dataBaseSource.insertAll(newsList)
                newsList.map { entityMapper(it) }
            } else {
                dataBaseSource.getAll().map { entityMapper(it) }
            }
        }
    }

    override fun setToken(token: String) {
        netService.setUserToken(token)
    }
}