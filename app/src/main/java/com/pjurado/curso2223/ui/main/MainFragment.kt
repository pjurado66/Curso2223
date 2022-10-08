package com.pjurado.curso2223.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentMainBinding
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.ui.detail.DetailFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = MoviesAdapter(movies){ movie ->
                navigateTo(movie)
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

    }

    private fun navigateTo(movie: Movie) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.EXTRA_MOVIE to movie)
        )

    }
}

private val movies = (1..100).map {
    Movie(
        "Película $it",
        "https://loremflickr.com/240/320/paris?lock=$it")
}