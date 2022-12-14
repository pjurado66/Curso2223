package com.pjurado.curso2223.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pjurado.curso2223.App
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentMainBinding
import com.pjurado.curso2223.ui.detail.DetailFragment
import kotlinx.coroutines.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding
    private val adapter = MoviesAdapter(){ movie -> viewModel.navigateTo(movie)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)


        viewModel.state.observe(viewLifecycleOwner){state ->
           binding.progress.visibility =  if (state.loading) VISIBLE else GONE
//LiveData y GET
/*             state.movies?.let {
                adapter.movies = state.movies
                adapter.notifyDataSetChanged()
            }*/
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    state.movies?.collect() {
                        adapter.movies = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_MOVIE to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_mainFragment_to_createMovieFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.fab.setOnClickListener {
            viewModel.navigateToCreate()
        }
    }
}
