package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.review.Review
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(): Observable<List<Movie>>
    fun updateMovies(): Observable<List<Movie>>
    fun getReviews(movieId: String): Observable<Review>
}
