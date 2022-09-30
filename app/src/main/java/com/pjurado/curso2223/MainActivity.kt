package com.pjurado.curso2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pjurado.curso2223.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            recycler.adapter = MoviesAdapter(movies)
        }



    }


}

private val movies = (1..100).map {
    Movie(
        "Película $it",
        "https://loremflickr.com/g/240/3200/paris/lock=$it")
}