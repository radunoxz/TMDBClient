package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.movie.Movie
import io.reactivex.Flowable

interface MovieCacheDataSource {
    fun getMoviesFromCache(): Flowable<List<Movie>>
    fun saveMoviesToCache(movies: List<Movie>)
}
