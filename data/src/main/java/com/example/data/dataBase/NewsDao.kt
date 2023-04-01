package com.example.data.dataBase

import androidx.room.*
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): Observable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)

    @Query("DELETE FROM news_table")
    fun deleteAll()
}