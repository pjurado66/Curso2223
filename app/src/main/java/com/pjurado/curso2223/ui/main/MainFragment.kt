package com.pjurado.curso2223.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentMainBinding
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.MoviesProvider
import com.pjurado.curso2223.ui.detail.DetailFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment(R.layout.fragment_main) {
    private val adapter = MoviesAdapter(){ movie -> navigateTo(movie)}

    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        if (adapter.itemCount == 0){
            loadItems()
        }

    }

    private fun loadItems() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.progress.visibility =View.VISIBLE
            val movies = withContext(Dispatchers.IO){ MoviesProvider.getMovies() }
            adapter.movies = movies
            adapter.notifyDataSetChanged()
            binding.progress.visibility = View.GONE
        }
    }

    private fun navigateTo(movie: Movie) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.EXTRA_MOVIE to movie)
        )

    }
}
