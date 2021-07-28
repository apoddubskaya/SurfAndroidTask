package com.example.surfandroidtask.data.repository

import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.retrofit.ApiService
import com.example.surfandroidtask.data.room.FavouritesDao
import com.example.surfandroidtask.data.room.FavouritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val IMAGE_URL = "https://image.tmdb.org/t/p/w154"

class ListRepository @Inject constructor(
    val apiService: ApiService,
    val favouritesDao: FavouritesDao
) {
    suspend fun getFilms(query: String): List<Film> {
        return withContext(Dispatchers.IO) {
            val favs = favouritesDao.loadFavourites()
            val favsSet = HashSet<Int>()
            for (f in favs)
                favsSet.add(f.filmId)

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
                    isFavourite = favsSet.contains(it.id)
                )
            }
        }
    }

    suspend fun addFavourite(filmId: Int) = favouritesDao
        .insertFavourite(FavouritesEntity(filmId))

    suspend fun removeFavourite(filmId: Int) = favouritesDao
        .deleteFavourite(FavouritesEntity(filmId))
}