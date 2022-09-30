package com.pjurado.curso2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pjurado.curso2223.databinding.ActivityDetailBinding
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "DetailActivity:Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent?.getParcelableExtra<Movie>(EXTRA_MOVIE) ?: throw IllegalStateException()

        supportActionBar?.title = movie.title

        Glide.with(binding.imagen)
            .load(movie.urlImagen)
            .into(binding.imagen)
    }
}