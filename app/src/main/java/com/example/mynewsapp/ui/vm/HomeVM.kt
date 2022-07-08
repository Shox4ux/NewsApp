package com.example.mynewsapp.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.core.helper.Constants.Companion.API_KEY
import com.example.mynewsapp.core.helper.ResponseWrapper
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.NewsResponse
import com.example.mynewsapp.core.network.client.ActiveService
import com.example.mynewsapp.core.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: Repository
    ) :ViewModel() {

    // This mutableLivedata can be added value but it should be added data by ui so it is private
    // and for ui we only use simple livedata to pass the data

     val newsLiveData = MutableLiveData<ResponseWrapper<NewsResponse>>()

    var pageCount = 1

    init {
        getResponse()
    }

    fun getResponse(){
        viewModelScope.launch {
            newsLiveData.postValue(ResponseWrapper.Loading())
            newsLiveData.postValue(checkResponseStatus(repository.getCallForBreakingNews("us",pageCount,API_KEY)))
        }
    }

    private fun checkResponseStatus(response: Response<NewsResponse>):ResponseWrapper<NewsResponse>{
       if (response.isSuccessful){
           response.body()?.let {
               return ResponseWrapper.Success(it)
           }
       }
        return ResponseWrapper.Error(response.message(),response.code())
    }




    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.saveArticle(article)
    }

    fun getSavedArticles() = repository.getSavedArticles()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.deleteSavedArticle(article)
    }






    }

