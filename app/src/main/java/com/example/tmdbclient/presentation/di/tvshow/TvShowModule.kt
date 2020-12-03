package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.tvshow.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.tvshow.UpdateTvShowsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.example.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TvShowModule {
    @Provides
    @TvShowScope
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory =
        TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)

}
