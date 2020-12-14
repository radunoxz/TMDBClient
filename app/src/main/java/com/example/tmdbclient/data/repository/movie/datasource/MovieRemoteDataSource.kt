package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.review.Review
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(): Observable<MovieList>
    fun getReviews(movieId: String): Single<Review>
}
