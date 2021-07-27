package com.example.surfandroidtask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.repository.ListRepository
import com.example.surfandroidtask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
        repository: ListRepository
): ViewModel() {

    private val _films: MutableLiveData<Resource<List<Film>>> = MutableLiveData()
    val films: LiveData<Resource<List<Film>>> get() = _films

    init {
        _films.value = Resource.Success(repository.getFilms())
    }
}