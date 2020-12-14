package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val cacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override fun getMovies(): Observable<List<Movie>> = getMoviesFromAPI()

    // TODO Remove suspend fun from datasource
    override fun updateMovies(): Observable<List<Movie>> = getMoviesFromAPI()
        .subscribeOn(Schedulers.io())
        .doOnNext { list ->
            Log.i("MYTAG", Thread.currentThread().id.toString())
            localDataSource.clearAll()
            localDataSource.saveMoviesToDB(list)
            cacheDataSource.saveMoviesToCache(list)
        }
        .observeOn(AndroidSchedulers.mainThread())


    private fun getMoviesFromAPI(): Observable<List<Movie>> = movieRemoteDataSource.getMovies()
        .subscribeOn(Schedulers.io())
        .doOnNext { list ->
            Log.i("MYTAG", Thread.currentThread().id.toString())
        }
        .observeOn(AndroidSchedulers.mainThread())
        .map { list ->
            list.movies
        }


//    private suspend fun getMoviesFromDB(): List<Movie> {
//        lateinit var movieList: List<Movie>
//        try {
//            movieList = localDataSource.getMovies()
//
//        } catch (exception: Exception) {
//            Log.e("MYTAG", exception.message.toString())
//        }
//        if (movieList.isNotEmpty()) {
//            return movieList
//        } else {
//            movieList = getMoviesFromAPI()
//            localDataSource.saveMoviesToDB(movieList)
//        }
//
//        return movieList
//    }
//
//    private suspend fun getMoviesFromCache(): List<Movie> {
//        lateinit var movieList: List<Movie>
//        try {
//            movieList = cacheDataSource.getMoviesFromCache()
//
//        } catch (exception: Exception) {
//            Log.e("MYTAG", exception.message.toString())
//        }
//        if (movieList.isNotEmpty()) {
//            return movieList
//        } else {
//            movieList = getMoviesFromDB()
//            cacheDataSource.saveMoviesToCache(movieList)
//        }
//
//        return movieList
//    }

}
