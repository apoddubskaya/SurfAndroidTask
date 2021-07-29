package com.example.surfandroidtask.util

sealed class Resource<out T> {
    class Success<out T>(val data: T): Resource<T>()
    object Error: Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
