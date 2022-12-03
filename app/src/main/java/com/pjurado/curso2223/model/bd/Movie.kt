package com.pjurado.curso2223.model.bd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.pjurado.curso2223.model.Movie as DomainMovie

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun Movie.toDomainMovie() = DomainMovie(
    title, posterPath, overview, originalLanguage, originalTitle, voteAverage
)
