package com.example.surfandroidtask.data.room

import androidx.room.*

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM ${FavouritesEntity.TABLE_NAME}")
    suspend fun loadFavourites(): List<FavouritesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavourite(favourite: FavouritesEntity)

    @Delete
    suspend fun deleteFavourite(favourite: FavouritesEntity)

}