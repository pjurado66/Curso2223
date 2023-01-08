package com.pjurado.curso2223.ui.main

import android.view.View
import androidx.lifecycle.*
import com.pjurado.curso2223.model.DbFirestore
import com.pjurado.curso2223.model.Movie
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    val movies = DbFirestore.getFlow()

    init {
        _state.value = _state.value?.copy(movies = DbFirestore.getFlow())

    //LiveData
    /*        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)

            DbFirestore.getAllObservable().observeForever {
                    _state.value = _state.value?.copy(loading = false, movies = it)
            }
        }*/

        //GET
/*        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false, movies = requestMovies())
        }*/
    }

    private suspend fun requestMovies(): List<Movie>  = DbFirestore.getAll()


    fun navigateTo(movie: Movie) {
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    fun navigateToCreate() {
        _state.value = _state.value?.copy(navigateToCreate = true)
    }

    fun navigateToCreateDone() {
        _state.value = _state.value?.copy(navigateToCreate = false)
    }

    data class UiState(
        val loading: Boolean = false,
        //val movies: List<Movie>? = null,
        val movies: Flow<List<Movie>>? = null,
        val navigateTo: Movie? = null,
        val navigateToCreate: Boolean = false
    )

}