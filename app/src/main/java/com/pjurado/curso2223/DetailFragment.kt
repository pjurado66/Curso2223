package com.pjurado.curso2223

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pjurado.curso2223.databinding.FragmentDetailBinding
import java.lang.IllegalStateException

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

