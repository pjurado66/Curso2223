package com.pjurado.curso2223.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pjurado.curso2223.R
import com.pjurado.curso2223.databinding.FragmentDetailBinding
import com.pjurado.curso2223.loadUrl
import com.pjurado.curso2223.model.Movie

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<Movie>(EXTRA_MOVIE)!!)
    }
    companion object{
        const val EXTRA_MOVIE = "DetailActivity:Movie"
    }

    //private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
            /*viewModel = ViewModelProvider(
                this,
                DetailViewModelFactory(movie = movie)
            )[DetailViewModel::class.java]
             */

        viewModel.movie.observe(viewLifecycleOwner){ movie ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = movie.title
            binding.imagen.loadUrl(movie.urlImagen)
        }
    }
}

