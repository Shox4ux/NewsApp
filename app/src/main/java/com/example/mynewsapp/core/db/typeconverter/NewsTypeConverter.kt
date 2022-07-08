package com.example.mynewsapp.core.db.typeconverter

import androidx.room.TypeConverter
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NewsTypeConverter {
    @TypeConverter
    fun fromStringToListArticle(source: String?): List<Article> {
        val type = object : TypeToken<List<Article?>?>(){}.type
        return Gson().fromJson(source,type)
    }
    @TypeConverter
    fun fromListArticleToString(article: List<Article?>?): String {
        val gson = Gson()
        return gson.toJson(article)
    }

    @TypeConverter
    fun fromStringToSource(source: String?): Source {
        val type=object : TypeToken<Source?>(){}.type
        return Gson().fromJson(source,type)
    }
    @TypeConverter
    fun fromSourceToString(source: Source?): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}