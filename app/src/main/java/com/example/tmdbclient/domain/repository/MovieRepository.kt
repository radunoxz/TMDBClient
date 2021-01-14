package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.review.Review
import io.reactivex.Observable

/**
 * Interface defining methods for how the data layer can pass Movie data to and from the Domain layer.
 * This is to be implemented by the domain layer, setting the requirements for the
 * operations that need to be implemented
 */
interface MovieRepository {
    fun getMovies(): Observable<List<Movie>>

    fun updateMovies(): Observable<List<Movie>>

    fun getReviews(movieId: String): Observable<Review>
}
