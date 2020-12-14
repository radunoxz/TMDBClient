package com.example.tmdbclient.domain.usecase.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.Observable

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Observable<List<Movie>> = movieRepository.getMovies().flatMap {
        Observable.fromIterable(it).flatMap {
//            movieRepository.getReviews(it.id.toString())
            Observable.just(it)
        }.toList()

    }

}
