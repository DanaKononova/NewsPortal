package com.example.data

import com.example.data.mappers.NewsArticleMapper
import com.example.data.mappers.NewsEntityMapper
import com.example.domain.Repository
import com.example.domain.models.NewsData
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsArticleMapper,
    private val netService: UserDataSource,
    private val dataBaseSource: DataBaseSource,
    private val entityMapper: NewsEntityMapper
) : Repository {
    override suspend fun getNews(query: String, isConnected: Boolean): Flow<List<NewsData>> {
        return withContext(Dispatchers.IO) {
                val obj =
                    service.getNews(query).execute().body() ?: throw Exception()
                val newsList = (obj.article ?: listOf()).map { mapper(it) }
                dataBaseSource.deleteAll()
                dataBaseSource.insertAll(newsList)
            dataBaseSource.getAll().map{ list ->
                list.map {
                    entityMapper(it)
                }
            }
        }
    }

    override suspend fun searchNews(query: String, isConnected: Boolean): List<NewsData> {
        return withContext(Dispatchers.IO) {
            val obj =
                service.getNews(query).execute().body() ?: throw Exception()
            val newsList = (obj.article ?: listOf()).map { mapper(it) }
            newsList.map{
                entityMapper(it)
            }
        }
    }

    override fun setToken(token: String) {
        netService.setUserToken(token)
    }
}