package com.pjurado.curso2223.model.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun findById(id: Int): Movie

    @Query("SELECT COUNT(id) FROM Movie")
    suspend fun movieCount(): Int

    @Insert(onConflict = REPLACE)
    suspend fun inserMovies(movies: List<Movie>)
}