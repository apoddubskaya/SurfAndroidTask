package com.example.surfandroidtask.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavouritesEntity.TABLE_NAME)
data class FavouritesEntity(
    @PrimaryKey val filmId: Int
) {
    companion object {
        const val TABLE_NAME = "favourites_entities_table"
    }
}
