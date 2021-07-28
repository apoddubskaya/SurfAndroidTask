package com.example.surfandroidtask.data.retrofit

import com.example.surfandroidtask.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?language=ru&api_key=${BuildConfig.apiKey}")
    suspend fun getFilms(): ApiResponse

    @GET("search/movie?api_key=${BuildConfig.apiKey}&language=ru")
    suspend fun searchFilms(@Query("query") query: String): ApiResponse
}