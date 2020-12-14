package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import com.example.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = getMoviesUseCase.execute()

    fun updateMovies() = updateMoviesUseCase.execute()

}
