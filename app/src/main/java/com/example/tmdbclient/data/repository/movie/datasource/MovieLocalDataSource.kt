package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {
    fun getMovies(): List<Movie>
    fun saveMoviesToDB(movies: List<Movie>)
    fun clearAll()
}
