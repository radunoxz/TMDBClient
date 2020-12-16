package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.movie.Movie
import io.reactivex.Flowable
import io.reactivex.Observable

interface MovieLocalDataSource {
    fun getMovies(): Flowable<List<Movie>>
    fun saveMoviesToDB(movies: List<Movie>)
    fun clearAll()
}
