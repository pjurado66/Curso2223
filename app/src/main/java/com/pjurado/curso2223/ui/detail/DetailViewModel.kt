package com.pjurado.curso2223.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pjurado.curso2223.model.DbFirestore
import com.pjurado.curso2223.model.Movie

class DetailViewModel(movie: Movie): ViewModel() {
    fun borraMovie() {
        DbFirestore.borraMovie(movie.value!!)
    }

    fun modificaTituloMovie() {
        val title = movie.value?.title
        _movie.value = _movie.value?.copy(title = "$title modificado")
        DbFirestore.modificaTitulo(_movie.value, title!!)
    }

    private val _movie = MutableLiveData(movie)
    val movie: LiveData<Movie> get() = _movie
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val movie: Movie): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(movie) as T
    }

}