package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieCacheDataSource = MovieCacheDataSourceImpl()

    @Provides
    @Singleton
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource = TvShowCacheDataSourceImpl()

    @Provides
    @Singleton
    fun provideArtistCacheDataSource(): ArtistCacheDataSource = ArtistCacheDataSourceImpl()
}
