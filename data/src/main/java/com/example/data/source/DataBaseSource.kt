package com.example.newsportal.source

import com.example.data.dataBase.NewsDao
import com.example.data.dataBase.NewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val userDao: NewsDao
) {
    suspend fun getAll() = withContext(Dispatchers.IO) {
        userDao.getAll()
    }

    suspend fun insertAll(news: List<NewsEntity>) = withContext(Dispatchers.IO) {
        userDao.insertAll(news)
    }

    suspend fun delete(news: List<NewsEntity>) = withContext(Dispatchers.IO) {
        userDao.delete(news)
    }
}