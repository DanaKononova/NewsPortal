package com.example.data.dataBase

import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)

    @Delete
    fun delete(news: List<NewsEntity>)
}