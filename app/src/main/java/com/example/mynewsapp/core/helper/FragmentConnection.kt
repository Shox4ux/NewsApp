package com.example.mynewsapp.core.helper

import com.example.mynewsapp.core.models.Article

interface FragmentConnection {
    fun onTransist(newsData: Article)
}