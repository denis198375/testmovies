package com.testmovie.testmovie.data.remote.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


import com.testmovie.testmovie.data.local.MoviesFilterType;
import com.testmovie.testmovie.data.local.model.Movie;
import com.testmovie.testmovie.data.remote.api.MovieService;

import java.util.concurrent.Executor;


public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    public MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    private final MovieService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;

    public MovieDataSourceFactory(MovieService movieService,
                                  Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.sortBy = sortBy;
        this.networkExecutor = networkExecutor;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource movieDataSource =
                new MoviePageKeyedDataSource(movieService, networkExecutor, sortBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
