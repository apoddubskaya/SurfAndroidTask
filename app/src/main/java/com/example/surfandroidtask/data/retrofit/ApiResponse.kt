package com.example.surfandroidtask.data.retrofit

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val results: List<ApiEntity>
)

data class ApiEntity(
    val id: Int,
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
)
