package com.pjurado.curso2223.ui.main

import android.view.View
import androidx.lifecycle.*
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.MoviesProvider
import com.pjurado.curso2223.model.server.RemoteConnection
import com.pjurado.curso2223.ui.detail.DetailViewModel
import kotlinx.coroutines.*

class MainViewModel(apiKey: String): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val result = RemoteConnection.service.popularMovies(apiKey)
            val movies = result.results.map {
                Movie(
                    it.title,
                    "https://image.tmdb.org/t/p/w185/" + it.posterPath
                )
            }
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

class MainViewModelFactory(private val apiKey: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(apiKey) as T
    }
}