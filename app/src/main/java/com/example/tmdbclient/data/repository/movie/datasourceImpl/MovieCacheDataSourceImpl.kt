package com.example.tmdbclient.data.repository.movie.datasourceImpl

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import io.reactivex.Flowable

/**
 * This class is an implementation of the Cache Data Source
 */
class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var moviesList: MutableList<Movie> = ArrayList()

    override fun getMoviesFromCache(): Flowable<List<Movie>> {
        Log.i("DLT", moviesList.toString())

        return Flowable.just(moviesList)
    }

    override fun saveMoviesToCache(movies: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(movies)
    }
}
