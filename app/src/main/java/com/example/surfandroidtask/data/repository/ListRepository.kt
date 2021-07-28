package com.example.surfandroidtask.data.repository

import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val IMAGE_URL = "https://image.tmdb.org/t/p/w154"

class ListRepository @Inject constructor(
    val apiService: ApiService
) {
    suspend fun getFilms(query: String): List<Film> {
        return withContext(Dispatchers.IO) {
            val response = if (query.isBlank())
                apiService.getFilms()
            else apiService.searchFilms(query)
            response.results.map {
                Film(
                    id = it.id,
                    title = it.title,
                    date = it.releaseDate,
                    description = it.description,
                    posterPath = IMAGE_URL + it.posterPath,
                    isFavourite = false
                )
            }
        }
    }
}