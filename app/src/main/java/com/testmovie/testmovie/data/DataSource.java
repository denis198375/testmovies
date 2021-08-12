package com.testmovie.testmovie.data;

import androidx.lifecycle.LiveData;


import com.testmovie.testmovie.data.local.MoviesFilterType;
import com.testmovie.testmovie.data.local.model.Movie;
import com.testmovie.testmovie.data.local.model.MovieDetails;
import com.testmovie.testmovie.data.local.model.RepoMoviesResult;
import com.testmovie.testmovie.data.local.model.Resource;

import java.util.List;

public interface DataSource {

    LiveData<Resource<MovieDetails>> loadMovie(long movieId);

    RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy);


}
