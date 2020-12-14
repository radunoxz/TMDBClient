package com.example.tmdbclient.domain.usecase.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.Observable

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Observable<List<Movie>> = movieRepository.updateMovies()
}
