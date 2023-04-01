package com.example.data.source

import com.example.data.dataBase.NewsDao
import com.example.data.dataBase.NewsEntity
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val userDao: NewsDao
) {
    fun getAll(): Observable<List<NewsEntity>> {
        return userDao.getAll()
    }

    fun insertAll(news: List<NewsEntity>) = userDao.insertAll(news)

    fun deleteAll() {
        userDao.deleteAll()
    }
}