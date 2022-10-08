package com.pjurado.curso2223.model

object MoviesProvider {
    fun getMovies(): List<Movie> {
        Thread.sleep(2000)
        return (1..100).map {
            Movie(
                "Película $it",
                "https://loremflickr.com/240/320/paris?lock=$it"
            )
        }
    }
}