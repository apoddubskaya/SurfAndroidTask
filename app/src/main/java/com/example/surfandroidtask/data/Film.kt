package com.example.surfandroidtask.data

data class Film(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val posterPath: String,
    var isFavourite: Boolean
)
