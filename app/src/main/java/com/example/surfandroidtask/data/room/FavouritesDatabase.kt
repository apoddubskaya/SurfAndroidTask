package com.example.surfandroidtask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouritesEntity::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}