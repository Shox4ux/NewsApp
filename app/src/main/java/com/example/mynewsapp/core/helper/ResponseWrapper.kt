package com.example.mynewsapp.core.helper

sealed class ResponseWrapper<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: Int? = null

) {
    class Success<T>(data: T): ResponseWrapper<T>(data)
    class Error<T>(message:String,errorCode:Int?, data: T? = null): ResponseWrapper<T>(data,message,errorCode)
    class Loading<T> :ResponseWrapper<T>()
}