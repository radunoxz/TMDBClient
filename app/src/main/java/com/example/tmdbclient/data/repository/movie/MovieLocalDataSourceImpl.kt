package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This class is a implementation of the Local Data Source
 */
class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun getMovies(): List<Movie> =
        movieDao.getMovies()


    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch { movieDao.saveMovies(movies) }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch { movieDao.deleteAllMovies() }
    }
}
