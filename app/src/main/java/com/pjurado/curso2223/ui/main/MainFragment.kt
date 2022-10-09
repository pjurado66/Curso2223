package com.pjurado.curso2223.ui.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentMainBinding
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.model.MoviesProvider
import com.pjurado.curso2223.ui.detail.DetailFragment
import kotlinx.coroutines.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private val adapter = MoviesAdapter(){ movie -> navigateTo(movie)}
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.progressVisible.observe(viewLifecycleOwner) { visible ->
            binding.progress.visibility = if (visible) VISIBLE else GONE
        }

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.movies = it
            adapter.notifyDataSetChanged()
        }

    }

    private fun navigateTo(movie: Movie) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.EXTRA_MOVIE to movie)
        )

    }
}
