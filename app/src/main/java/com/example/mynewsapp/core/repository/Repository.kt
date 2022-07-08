package com.example.mynewsapp.core.repository

import androidx.lifecycle.LiveData
import com.example.mynewsapp.core.db.dao.DaoInterface
import com.example.mynewsapp.core.db.room.MainDatabase
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.NewsResponse
import com.example.mynewsapp.core.network.client.ActiveService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val activeService: ActiveService,
    private val db: MainDatabase) {

    val dao = db.getDaoInterface()

    suspend fun getCallForSearchNews(page:Int,apiKey:String,dateFrom:String,dateTo:String,sortBy:String,topic:String):Response<NewsResponse>{
       return activeService.requestForSearchNews(page,apiKey,dateFrom,dateTo,sortBy,topic)
    }

    suspend fun getCallForBreakingNews(countryCode:String,page:Int,apiKey:String):Response<NewsResponse>{
       return activeService.requestForBreakingNews(countryCode, page,apiKey)
    }





    suspend fun saveArticle(article: Article) = dao.saveArticle(article)

     fun getSavedArticles() = dao.getSavedArticles()

    suspend fun deleteSavedArticle(article: Article) = dao.deleteSavedArticle(article)



    suspend fun putNewsDataIntoCache(newsResponse: NewsResponse) = dao.saveForCache(newsResponse)

    suspend fun getNewsDataFromCache() = dao.getNewsFromCache()



}