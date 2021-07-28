package com.example.surfandroidtask.ui.viewmodel

import androidx.lifecycle.*
import com.example.surfandroidtask.data.Film
import com.example.surfandroidtask.data.repository.ListRepository
import com.example.surfandroidtask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    var repository: ListRepository
): ViewModel() {

    private val _films: MutableLiveData<Resource<List<Film>>> = MutableLiveData()
    val films: LiveData<Resource<List<Film>>> get() = _films

    init {
        getFilmsData()
    }

    private var lastQuery = ""

    fun searchFilms(query: String?) {
        if (query == null) {
            _films.value = Resource.Error("todo: some error")
            return
        }
        if (query == lastQuery)
            return
        lastQuery = query
        getFilmsData(query)
    }

    private fun getFilmsData(query: String = "")  {
        viewModelScope.launch {
            _films.value = Resource.Loading
            try {
                _films.value = Resource.Success(repository.getFilms(query))
            }
            catch(e: Exception) {
                _films.value = Resource.Error(e.message ?: "todo: empty exception msg")
            }
        }
    }
}