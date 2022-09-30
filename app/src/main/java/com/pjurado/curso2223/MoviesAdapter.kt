package com.pjurado.curso2223

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pjurado.curso2223.databinding.ViewMovieBinding

class MoviesAdapter(val movies: List<Movie>, val listener: (Movie) -> Unit):
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            listener(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewMovieBinding.bind(view)
         fun bind(movie: Movie){
             binding.title.text = movie.title

             Glide.with(binding.imagen)
                 .load(movie.urlImagen)
                 .into(binding.imagen)
         }
    }
}