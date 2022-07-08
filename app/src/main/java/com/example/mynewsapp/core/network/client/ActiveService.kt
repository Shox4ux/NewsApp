package com.example.mynewsapp.core.network.client

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.mynewsapp.core.models.NewsResponse
import retrofit2.Response

interface ActiveService {

    @GET("v2/everything")
    suspend fun requestForSearchNews(
        @Query("page")
        page :Int,
        @Query("apiKey")
        apiKey:String,
        @Query("from")
        from :String,
        @Query("to")
        to :String,
        @Query("sortBy")
        sortBy :String,
        @Query("q")
        topic:String
    ): Response<NewsResponse>



    @GET("v2/top-headlines")
    suspend fun requestForBreakingNews(
        @Query("country")
        countryCode:String,
        @Query("page")
        pageNumber:Int,
        @Query("apiKey")
        apiKey:String,
    ) : Response<NewsResponse>
}