package com.pjurado.curso2223

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pjurado.curso2223.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = MoviesAdapter(movies){ movie ->
                // TODO Navegacion
            }


        }
    }
}

private val movies = (1..100).map {
    Movie(
        "Película $it",
        "https://loremflickr.com/240/320/paris?lock=$it")
}