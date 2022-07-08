package com.example.mynewsapp.core.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynewsapp.core.db.dao.DaoInterface
import com.example.mynewsapp.core.db.typeconverter.NewsTypeConverter
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.NewsResponse

@Database(
    entities = [Article::class,NewsResponse::class],
    version = 1, exportSchema = false
)
@TypeConverters(value = [NewsTypeConverter::class])
abstract class MainDatabase: RoomDatabase(){
    abstract fun getDaoInterface(): DaoInterface
}