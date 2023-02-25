package com.example.newsportal.data

import com.example.newsportal.data.dataBase.NewsEntity
import com.example.newsportal.data.mappers.NewsArticleMapper
import com.example.newsportal.domain.Repository
import com.example.newsportal.source.DataBaseSource
import com.example.newsportal.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsArticleMapper,
    private val netService: UserDataSource,
    private val dataBaseSource: DataBaseSource
) : Repository {
    override suspend fun getNews(isConnected: Boolean): List<NewsEntity> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val obj =
                    service.getNews(netService.getUserToken()).execute().body() ?: throw Exception()
                val newsList = (obj.article ?: listOf()).map { mapper(it) }
                dataBaseSource.delete(dataBaseSource.getAll())
                dataBaseSource.insertAll(newsList)
                newsList
            } else {
                dataBaseSource.getAll()
            }
        }
    }

    override fun setToken(token: String) {
        netService.setUserToken(token)
    }

    override suspend fun getAll(): List<NewsEntity> {
        return withContext(Dispatchers.IO) {
            dataBaseSource.getAll()
        }
    }

    override suspend fun insertAll(news: List<NewsEntity>) {
        return withContext(Dispatchers.IO) {
            dataBaseSource.insertAll(news)
        }
    }

    override suspend fun delete(news: List<NewsEntity>) {
        return withContext(Dispatchers.IO) {
            dataBaseSource.delete(news)
        }
    }
}