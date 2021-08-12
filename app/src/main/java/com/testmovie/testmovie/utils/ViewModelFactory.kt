package com.testmovie.testmovie.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testmovie.testmovie.Movies.MoviesViewModel
import com.testmovie.testmovie.data.MovieRepository

class ViewModelFactory private constructor(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @JvmStatic
        fun getInstance(repository: MovieRepository): ViewModelFactory {
            return ViewModelFactory(repository)
        }
    }
}