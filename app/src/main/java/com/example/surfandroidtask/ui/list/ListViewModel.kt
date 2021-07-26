package com.example.surfandroidtask.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
        repository: ListRepository
): ViewModel() {

    private val _films: MutableLiveData<List<Film>> = MutableLiveData()
    val films: LiveData<List<Film>> get() = _films

    init {
        _films.value = repository.getFilms()
    }
}