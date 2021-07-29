package com.example.surfandroidtask.data.repository

import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.retrofit.ApiService
import com.example.surfandroidtask.data.room.FavouritesDao
import com.example.surfandroidtask.data.room.FavouritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

const val IMAGE_URL = "https://image.tmdb.org/t/p/w154"

class ListRepository @Inject constructor(
    val apiService: ApiService,
    val favouritesDao: FavouritesDao
) {
    suspend fun getFilms(query: String): List<Film> {
        return withContext(Dispatchers.IO) {
            val favourites = favouritesDao.loadFavourites()
            val favouritesSet = HashSet<Int>()
            for (f in favourites)
                favouritesSet.add(f.filmId)

            val response = if (query.isBlank())
                apiService.getFilms()
            else apiService.searchFilms(query)
            response.results.map {
                Film(
                    id = it.id,
                    title = it.title,
                    date = formatDate(it.releaseDate),
                    description = it.description,
                    posterPath = IMAGE_URL + it.posterPath,
                    isFavourite = favouritesSet.contains(it.id)
                )
            }
        }
    }

    private fun formatDate(dateString: String?): String {
        if (dateString == null)
            return ""
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
        val date = try {
            parser.parse(dateString)
        } catch (e: Exception) {
            return ""
        }
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        return if (date == null) dateString else formatter.format(date)
    }

    suspend fun addFavourite(filmId: Int) = favouritesDao
        .insertFavourite(FavouritesEntity(filmId))

    suspend fun removeFavourite(filmId: Int) = favouritesDao
        .deleteFavourite(FavouritesEntity(filmId))
}