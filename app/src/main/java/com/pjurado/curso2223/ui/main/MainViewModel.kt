package com.pjurado.curso2223.ui.main

import android.view.View
import androidx.lifecycle.*
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.MoviesProvider
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _progressVisible = MutableLiveData(false)
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _progressVisible.value = true
            _movies.value =  withContext(Dispatchers.IO){MoviesProvider.getMovies()}
            _progressVisible.value = false
        }
    }


}