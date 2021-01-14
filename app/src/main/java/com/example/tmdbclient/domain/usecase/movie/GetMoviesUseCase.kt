package com.example.tmdbclient.domain.usecase.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This usecase is used to retrieve a list of movies with the correspondent review retrieved by
 * the id of the movie from [MovieRepository] and it should be used in the correspondent ViewModel
 */
class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Observable<List<Movie>> =
        movieRepository.getMovies().take(1).flatMapIterable { it }.flatMap { movie ->
            movieRepository.getReviews(movie.id.toString()).flatMap { review ->
                movie.review = review.results.firstOrNull()?.content
                    ?: "This movie doesn't have a review"
                Log.i(GetMoviesUseCase::class.java.canonicalName, movie.toString())
                Observable.just(movie)
            }.subscribeOn(Schedulers.io())
        }.toList().toObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
