package com.pjurado.curso2223.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DbFirestore {
    const val COLLECTION_MOVIES = "movies"
    suspend fun getAll(): List<Movie> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
            .get()
            .await()
        val movies = mutableListOf<Movie>()
        for (documentSnapshot in snapshot){
            val movie = documentSnapshot.toObject(Movie::class.java)
            movies.add(movie)
        }
        return movies
    }

    suspend fun createMovie(movie: Movie){
        FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
            .add(movie)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_MOVIES, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_MOVIES, it.toString())
            }

    }

    fun borraMovie(movie: Movie) {
        FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
            .whereEqualTo("title", movie.title)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_MOVIES, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_MOVIES, it.toString())
                        }
                }
            }
    }

    fun modificaTitulo(movie: Movie?, title: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
            .whereEqualTo("title", title)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
                        .document(id)
                        .update("title", movie?.title)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_MOVIES, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_MOVIES, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Movie>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Movie>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_MOVIES)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Movie>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Movie::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

}