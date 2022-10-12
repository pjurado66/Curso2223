package com.pjurado.curso2223.ui.main

import android.view.View
import androidx.lifecycle.*
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.MoviesProvider
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val movies =  withContext(Dispatchers.IO){MoviesProvider.getMovies()}
            _state.value = _state.value?.copy(loading = false, movies = movies)
        }
    }

    fun navigateTo(movie: Movie) {
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null
    )

}