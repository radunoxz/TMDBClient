package com.example.tmdbclient.data.repository.movie.datasourceImpl

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import java.util.ArrayList

/**
 * This class is a implementation of the Cache Data Source
 */
class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesList = ArrayList<Movie>()

    override fun getMoviesFromCache(): List<Movie> = moviesList

    override fun saveMoviesToCache(movies: List<Movie>) {
        moviesList.clear()
        moviesList = ArrayList(movies)
    }
}
