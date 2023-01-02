package com.pjurado.curso2223.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentCreateMovieBinding
import com.pjurado.curso2223.databinding.FragmentDetailBinding
import com.pjurado.curso2223.model.Movie
import com.pjurado.curso2223.ui.detail.DetailFragment


class CreateMovieFragment : Fragment(R.layout.fragment_create_movie) {
    private val viewModel: CreateMovieViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateMovieBinding.bind(view)

        binding.button.setOnClickListener {
            val movie = Movie(binding.editTextTextPersonName.text.toString())
            viewModel.createMovie(movie)
            findNavController().navigate(
                R.id.action_createMovieFragment_to_mainFragment)
        }
    }
}