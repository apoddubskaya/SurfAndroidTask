package com.example.surfandroidtask.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.surfandroidtask.data.room.FavouritesDao
import com.example.surfandroidtask.data.room.FavouritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context): FavouritesDatabase = Room
        .databaseBuilder(
            context,
            FavouritesDatabase::class.java,
            "favourites.db"
        )
        .build()

    @Provides
    fun providesDao(db: FavouritesDatabase): FavouritesDao = db.favouritesDao()
}