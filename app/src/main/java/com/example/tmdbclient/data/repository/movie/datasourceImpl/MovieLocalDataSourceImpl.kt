package com.example.tmdbclient.data.repository.movie.datasourceImpl

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import io.reactivex.Flowable

/**
 * This class is a implementation of the Local Data Source
 */
class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun getMovies(): Flowable<List<Movie>> {
        return movieDao.getMovies()
    }


    override fun saveMoviesToDB(movies: List<Movie>) {
        movieDao.saveMovies(movies)
    }

    override fun clearAll() {
        movieDao.deleteAllMovies()
    }
}
