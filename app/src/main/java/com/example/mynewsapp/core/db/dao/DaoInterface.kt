package com.example.mynewsapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.NewsResponse

@Dao
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article): Long


    @Query("SELECT * FROM articles")
     fun getSavedArticles(): LiveData<List<Article>>


    @Delete
    suspend fun deleteSavedArticle(article: Article)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForCache(newsResponse: NewsResponse)


    @Query("SELECT * FROM news_app LIMIT 1")
    suspend fun getNewsFromCache(): LiveData<NewsResponse>

}