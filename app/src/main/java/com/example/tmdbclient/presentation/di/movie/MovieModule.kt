package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieModule {
    @Provides
    @MovieScope
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory =
        MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
}
