package com.pjurado.curso2223.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MoviesProvider {
    suspend fun getMovies(tipo: String): List<Movie> = withContext(Dispatchers.IO){
        Thread.sleep(2000)
        (1..100).map {Movie("Película $it","https://loremflickr.com/240/320/$tipo?lock=$it") }
    }
}