package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @Provides
    @ArtistScope
    fun provideArtistViewModelFactory(
        getArtistUseCase: GetArtistUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory =
        ArtistViewModelFactory(getArtistUseCase, updateArtistsUseCase)
}
