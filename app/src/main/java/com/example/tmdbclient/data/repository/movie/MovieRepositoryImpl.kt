package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.review.Review
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Provides an implementation of the [MovieRepository] interface for communicating to and from
 * data sources.
 */

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val cacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override fun getMovies(): Observable<List<Movie>> = getMoviesFromCache().toObservable()

    // TODO Remove suspend fun from datasource
    override fun updateMovies(): Observable<List<Movie>> = getMoviesFromAPI()
        .doOnNext { list ->
            Log.i("MYTAG", Thread.currentThread().id.toString())
            localDataSource.clearAll()
            localDataSource.saveMoviesToDB(list)
            cacheDataSource.saveMoviesToCache(list)
        }

    override fun getReviews(movieId: String): Observable<Review> =
        movieRemoteDataSource.getReviews(movieId)

    private fun getMoviesFromAPI(): Observable<List<Movie>> = movieRemoteDataSource.getMovies()
        .doOnNext { list ->
            Log.i("MYTAG", Thread.currentThread().id.toString())
        }
        .map { list ->
            list.movies
        }

    private fun getMoviesFromDB(): Flowable<List<Movie>> {
        Log.i("MYTAG", "getMoviesFromDB")
        return localDataSource.getMovies().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getMoviesFromAPI().map {
                localDataSource.saveMoviesToDB(it)
                it
            }.toFlowable(BackpressureStrategy.ERROR)
        )
    }

    private fun getMoviesFromCache(): Flowable<List<Movie>> {
        return cacheDataSource.getMoviesFromCache().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getMoviesFromDB().map {
                cacheDataSource.saveMoviesToCache(it)
                it
            })
    }
}
