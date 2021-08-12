package com.testmovie.testmovie.data.local

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testmovie.testmovie.data.local.model.Movie
import com.testmovie.testmovie.databinding.ItemMovieBinding

class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bindTo(movie: Movie?) {
        binding.movie = movie
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        fun create(parent: ViewGroup): MovieViewHolder {
            // Inflate
            val layoutInflater = LayoutInflater.from(parent.context)
            // Create the binding
            val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}