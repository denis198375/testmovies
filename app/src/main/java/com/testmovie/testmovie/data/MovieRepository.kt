package com.testmovie.testmovie.data

import androidx.lifecycle.LiveData
import com.testmovie.testmovie.data.local.MoviesFilterType
import com.testmovie.testmovie.data.local.MoviesLocalDataSource
import com.testmovie.testmovie.data.local.model.Movie
import com.testmovie.testmovie.data.local.model.MovieDetails
import com.testmovie.testmovie.data.local.model.RepoMoviesResult
import com.testmovie.testmovie.data.local.model.Resource
import com.testmovie.testmovie.data.remote.MoviesRemoteDataSource
import com.testmovie.testmovie.data.remote.api.ApiResponse
import com.testmovie.testmovie.utils.AppExecutors

class MovieRepository private constructor(
    private val mLocalDataSource: MoviesLocalDataSource,
    private val mRemoteDataSource: MoviesRemoteDataSource,
    private val mExecutors: AppExecutors
) : DataSource {
    override fun loadMovie(movieId: Long): LiveData<Resource<MovieDetails?>> {
        return object : NetworkBoundResource<MovieDetails?, Movie?>(mExecutors) {
            override fun saveCallResult(item: Movie) {
                mLocalDataSource.saveMovie(item)
            }

            override fun shouldFetch(data: MovieDetails?): Boolean {
                return data == null // only fetch fresh data if it doesn't exist in database
            }

            override fun loadFromDb(): LiveData<MovieDetails?> {
                return mLocalDataSource.getMovie(movieId)
            }

            override fun createCall(): LiveData<ApiResponse<Movie?>> {
                return mRemoteDataSource.loadMovie(movieId)
            }

            override fun onFetchFailed() {
                // ignored
            }
        }.asLiveData
    }

    override fun loadMoviesFilteredBy(sortBy: MoviesFilterType): RepoMoviesResult {
        return mRemoteDataSource.loadMoviesFilteredBy(sortBy)
    }



    companion object {
        @Volatile
        private var sInstance: MovieRepository? = null
        @JvmStatic
        fun getInstance(
            localDataSource: MoviesLocalDataSource,
            remoteDataSource: MoviesRemoteDataSource,
            executors: AppExecutors
        ): MovieRepository? {
            if (sInstance == null) {
                synchronized(MovieRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = MovieRepository(localDataSource, remoteDataSource, executors)
                    }
                }
            }
            return sInstance
        }
    }
}