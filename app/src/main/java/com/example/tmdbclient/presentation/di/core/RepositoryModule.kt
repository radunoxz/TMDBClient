package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataModule: MovieRemoteDataSource,
        movieLocalDataModule: MovieLocalDataSource,
        movieCacheDataModule: MovieCacheDataSource
    ): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataModule, movieLocalDataModule, movieCacheDataModule)

    @Provides
    @Singleton
    fun provideTvShowRepository(
        remoteDataModule: TvShowRemoteDataSource,
        localDataModule: TvShowLocalDataSource,
        cacheDataModule: TvShowCacheDataSource
    ): TvShowRepository =
        TvShowRepositoryImpl(remoteDataModule, localDataModule, cacheDataModule)

    @Provides
    @Singleton
    fun provideArtistRepository(
        remoteDataModule: ArtistRemoteDataSource,
        localDataModule: ArtistLocalDataSource,
        cacheDataModule: ArtistCacheDataSource
    ): ArtistRepository =
        ArtistRepositoryImpl(remoteDataModule, localDataModule, cacheDataModule)
}
