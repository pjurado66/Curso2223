package com.pjurado.curso2223

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pjurado.curso2223.model.bd.MovieDatabase

class App: Application() {
    lateinit var db: MovieDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(this,MovieDatabase::class.java,"Movie-db")
            .build()
    }
}