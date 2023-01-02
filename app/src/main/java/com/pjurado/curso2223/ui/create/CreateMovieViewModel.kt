package com.pjurado.curso2223.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjurado.curso2223.model.DbFirestore
import com.pjurado.curso2223.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateMovieViewModel: ViewModel() {

    fun createMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createMovie(movie)
        }

    }
}