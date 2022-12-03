package com.pjurado.curso2223.model.server


import com.google.gson.annotations.SerializedName
import com.pjurado.curso2223.model.Movie as DomainMovie
import com.pjurado.curso2223.model.bd.Movie as DbMovie

data class RemoteResult(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class Movie(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

fun Movie.toDbMovie(): DbMovie = DbMovie(
    id,
    adult,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    originalLanguage,
    originalTitle,
    popularity,
    title,
    video,
    voteAverage,
    voteCount
)