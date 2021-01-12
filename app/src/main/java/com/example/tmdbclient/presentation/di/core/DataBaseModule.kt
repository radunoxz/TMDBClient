package com.example.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideMovieDataBase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(tmdbDataBase: TMDBDatabase): MovieDao {
        return tmdbDataBase.movieDao()
    }

    @Provides
    @Singleton
    fun provideTvShowDao(tmdbDataBase: TMDBDatabase): TvShowDao {
        return tmdbDataBase.tvDao()
    }

    @Provides
    @Singleton
    fun provideArtistDataBase(tmdbDataBase: TMDBDatabase): ArtistDao {
        return tmdbDataBase.artistDao()
    }
}
