package com.example.data.source

import com.example.data.dataBase.NewsDao
import com.example.data.dataBase.NewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        userDao.deleteAll()
    }
}