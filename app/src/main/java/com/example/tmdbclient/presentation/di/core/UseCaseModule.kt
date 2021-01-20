package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import com.example.tmdbclient.domain.usecase.tvshow.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.tvshow.UpdateTvShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCase(movieRepository)

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase =
        UpdateMoviesUseCase(movieRepository)

    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase =
        GetTvShowsUseCase(tvShowRepository)

    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase =
        UpdateTvShowsUseCase(tvShowRepository)

    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository): GetArtistUseCase =
        GetArtistUseCase(artistRepository)

    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase =
        UpdateArtistsUseCase(artistRepository)
}
