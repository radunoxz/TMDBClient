package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import java.util.ArrayList

/**
 * This class is a implementation of the Cache Data Source
 */
class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> = moviesList

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        moviesList.clear()
        moviesList = ArrayList(movies)
    }
}
