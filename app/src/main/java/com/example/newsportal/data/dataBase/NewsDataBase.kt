package com.example.newsportal.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun userDao(): NewsDao
}