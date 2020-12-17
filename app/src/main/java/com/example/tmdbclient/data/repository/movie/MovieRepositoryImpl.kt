package com.example.tmdbclient.data.repository.movie

import android.annotation.SuppressLint
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
import java.util.concurrent.Flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val cacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override fun getMovies(): Observable<List<Movie>> = getMoviesFromDB().toObservable()

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

    @SuppressLint("CheckResult")
    private fun getMoviesFromDB(): Flowable<List<Movie>> {
        Log.i("MYTAG", "getMoviesFromDB")
        return localDataSource.getMovies().flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            }else {
                getMoviesFromAPI().map {
                    Log.i("MYTAG", it.toString())
                    localDataSource.saveMoviesToDB(it)
                    it
                }.toFlowable(BackpressureStrategy.ERROR)
//                Flowable.empty()
            }
        }

//        lateinit var movieList: List<Movie>
//        try {
//            movieList = localDataSource.getMovies().flatMap {
//                Flowable.fromIterable(movieList)
//                it
//            }
//
//        } catch (exception: Exception) {
//            Log.e("MYTAG", exception.message.toString())
//        }
//        if (localDataSource.getMovies().) {
//            return movieList
//        } else {
//            movieList = getMoviesFromAPI().toFlowable(BackpressureStrategy.BUFFER)
//            getMoviesFromAPI().map { it ->
//                localDataSource.saveMoviesToDB(it)
//                it
//            }
//        }
//
//        return movieList
    }

//    private fun getMoviesFromDB(): Flowable<List<Movie>> {
//        Log.i("MYTAG", "getMoviesFromDB")
//        return localDataSource.getMovies().flatMap {
//            if (it.isNotEmpty()) {
//                Flowable.just(it)
//            }else {
//
//                Flowable.empty()
//            }
//        }.switchIfEmpty {
//            getMoviesFromAPI().map {
//                Log.i("MYTAG", it.toString())
//                localDataSource.saveMoviesToDB(it)
//                it
//            }
//        }
//    }


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

    private fun getMoviesFromCache(): Flowable<List<Movie>> =
        cacheDataSource.getMoviesFromCache().switchIfEmpty {
            getMoviesFromDB().map {
                cacheDataSource.saveMoviesToCache(it)
            }
        }
}
