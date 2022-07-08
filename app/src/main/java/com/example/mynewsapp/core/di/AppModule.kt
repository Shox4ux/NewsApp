package com.example.mynewsapp.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mynewsapp.core.app.App
import com.example.mynewsapp.core.db.dao.DaoInterface
import com.example.mynewsapp.core.db.room.MainDatabase
import com.example.mynewsapp.core.helper.Constants.Companion.BASE_URL
import com.example.mynewsapp.core.network.client.ActiveService
import com.example.mynewsapp.core.repository.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideLogging():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideActiveService(retrofit: Retrofit):ActiveService{
        return retrofit.create(ActiveService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        activeService: ActiveService,
        db:MainDatabase
    ):Repository{
        return Repository(activeService,db)
    }

    @Provides
    @Singleton
    fun provideApp():App{
        return  App()
    }


    @Provides
    @Singleton
    fun provideDatabase(app: App): MainDatabase {
       return Room.databaseBuilder(app,MainDatabase::class.java,"main_database").build()
    }






}