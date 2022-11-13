package com.pjurado.curso2223.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
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

        //(requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewModel.movie.observe(viewLifecycleOwner){ movie ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = movie.title
            binding.imagen.loadUrl(movie.urlImagen)
            binding.descripcion.text = movie.description
            bindingDetail(binding.detalles, movie)
        }
    }

    private fun bindingDetail(detalles: TextView, movie: Movie) {
        detalles.text = buildSpannedString {
            bold { append("Idioma Original: ") }
            appendLine(movie.original_languague)

            append()

            bold { append("Título Original: ") }
            appendLine(movie.title_original)
            append()
            bold { append("Valoración: ") }
            appendLine(movie.valoracion.toString())
        }
    }


}

