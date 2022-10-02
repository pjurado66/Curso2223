package com.pjurado.curso2223

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.pjurado.curso2223.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = MoviesAdapter(movies){ movie ->
                navigateto(movie)
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun navigateto(movie: Movie) {
        val fragment = DetailFragment()
        fragment.arguments = bundleOf(DetailFragment.EXTRA_MOVIE to movie)

        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_right_in,
                R.anim.slide_left_out,
                R.anim.slide_left_in,
                R.anim.slide_right_out)
            .replace(R.id.fragment_container_view,fragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}

private val movies = (1..100).map {
    Movie(
        "Película $it",
        "https://loremflickr.com/240/320/paris?lock=$it")
}