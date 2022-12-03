package com.pjurado.curso2223.ui.main

import android.view.View
import androidx.lifecycle.*
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.bd.MovieDao
import com.pjurado.curso2223.model.bd.toDomainMovie
import com.pjurado.curso2223.model.server.RemoteConnection
import com.pjurado.curso2223.model.server.toDbMovie
import com.pjurado.curso2223.ui.detail.DetailViewModel
import kotlinx.coroutines.*

class MainViewModel(private val apiKey: String, private val movieDao: MovieDao): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false, movies = requestMovies())
        }
    }

    private suspend fun requestMovies(): List<Movie>{
        if (movieDao.movieCount() == 0) {
            val movies = RemoteConnection.service.popularMovies(apiKey).results
            movieDao.inserMovies(movies.map{it.toDbMovie()})
        }
        return movieDao.getAll().map { it.toDomainMovie() }
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

class MainViewModelFactory(private val apiKey: String, private val movieDao: MovieDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(apiKey, movieDao) as T
    }
}