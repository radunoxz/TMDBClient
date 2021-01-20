package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService, BuildConfig.API_KEY)
    }
}
