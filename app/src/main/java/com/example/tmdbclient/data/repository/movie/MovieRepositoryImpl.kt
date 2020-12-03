package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val cacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMoviews = getMoviesFromAPI()
        localDataSource.clearAll()
        localDataSource.saveMoviesToDB(newListOfMoviews)
        cacheDataSource.saveMoviesToCache(newListOfMoviews)

        return newListOfMoviews
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }
        return movieList
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = localDataSource.getMovies()

        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            localDataSource.saveMoviesToDB(movieList)
        }

        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = cacheDataSource.getMoviesFromCache()

        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            cacheDataSource.saveMoviesToCache(movieList)
        }

        return movieList
    }

}
