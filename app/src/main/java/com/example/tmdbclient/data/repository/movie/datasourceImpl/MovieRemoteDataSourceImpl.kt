package com.example.tmdbclient.data.repository.movie.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.review.Review
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This class is a implementation of the Remote Data Source
 */

class MovieRemoteDataSourceImpl
@Inject constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {

    override fun getMovies(): Observable<MovieList> =
        tmdbService.getPopularMovies(apiKey)

    override fun getReviews(movieId: String): Observable<Review> =
        tmdbService.getReviews(movieId, apiKey)
}
