package com.testmovie.testmovie.data.remote.api

import androidx.lifecycle.LiveData
import com.testmovie.testmovie.data.local.model.Movie
import com.testmovie.testmovie.data.local.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<MoviesResponse?>?

    @GET("movie/{id}?append_to_response=videos,credits,reviews")
    fun getMovieDetails(@Path("id") id: Long): LiveData<ApiResponse<Movie?>?>?
}