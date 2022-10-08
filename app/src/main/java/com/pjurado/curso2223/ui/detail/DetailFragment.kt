package com.pjurado.curso2223.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentDetailBinding
import com.pjurado.curso2223.loadUrl
import com.pjurado.curso2223.model.Movie

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object{
        const val EXTRA_MOVIE = "DetailActivity:Movie"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        val movie = arguments?.getParcelable<Movie>(EXTRA_MOVIE)

        if (movie != null) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = movie.title
            binding.imagen.loadUrl(movie.urlImagen)
        }
    }

}

