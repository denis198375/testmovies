package com.testmovie.testmovie.utils

import android.content.Context
import com.testmovie.testmovie.data.MovieRepository
import com.testmovie.testmovie.data.MovieRepository.Companion.getInstance
import com.testmovie.testmovie.data.local.MoviesDatabase.Companion.getInstance
import com.testmovie.testmovie.data.local.MoviesLocalDataSource
import com.testmovie.testmovie.data.remote.MoviesRemoteDataSource
import com.testmovie.testmovie.data.remote.api.ApiClient
import com.testmovie.testmovie.utils.ViewModelFactory.Companion.getInstance

object Injection {
    fun provideMoviesRemoteDataSource(): MoviesRemoteDataSource {
        val apiService = ApiClient.getInstance()
        val executors = AppExecutors.instance
        return MoviesRemoteDataSource.getInstance(apiService, executors)
    }

    fun provideMoviesLocalDataSource(context: Context): MoviesLocalDataSource {
        val database = getInstance(context.applicationContext)
        return MoviesLocalDataSource.getInstance(database)
    }

    fun provideMovieRepository(context: Context): MovieRepository? {
        val remoteDataSource = provideMoviesRemoteDataSource()
        val localDataSource = provideMoviesLocalDataSource(context)
        val executors = AppExecutors.instance
        return executors?.let {
            getInstance(
                localDataSource,
                remoteDataSource,
                it
            )
        }
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val repository = provideMovieRepository(context)
        return getInstance(repository!!)
    }
}