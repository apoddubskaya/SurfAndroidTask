package com.example.surfandroidtask.data

import javax.inject.Inject

class ListRepository @Inject constructor() {
    fun getFilms(): List<Film> {
        return listOf(
                Film(0, "title0", "overview0", "date0", false),
                Film(1, "title1", "overview1", "date1", false),
                Film(2, "title2", "overview2", "date2", false)
        )
    }
}