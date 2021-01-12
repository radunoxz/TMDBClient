package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.review.Review
import io.reactivex.Observable

interface MovieRemoteDataSource {
    fun getMovies(): Observable<MovieList>
    fun getReviews(movieId: String): Observable<Review>
}
