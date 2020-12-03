package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowCacheDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowRemoteDataSourceImpl
import com.example.tmdbclient.domain.repository.TvShowRepository
import java.lang.Exception

class TvShowRepositoryImpl(
    private val remoteDataSource: TvShowRemoteDataSourceImpl,
    private val localDataSource: TvShowLocalDataSourceImpl,
    private val cachedDataSource: TvShowCacheDataSourceImpl
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? = getTvShowsFromCache()

    override suspend fun updateTvShows(): List<TvShow>? {
        val newTvShowsList: List<TvShow> = getTvShowsFromRemote()
        localDataSource.clearAll()
        localDataSource.saveTvShowsToDB(newTvShowsList)
        cachedDataSource.saveTvShowsToCache(newTvShowsList)

        return newTvShowsList
    }

    private suspend fun getTvShowsFromRemote(): List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try {
            val response = remoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShowsList = body.tvShows
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return tvShowsList
    }

    private suspend fun getTvShowsFromLocal(): List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try {
            tvShowsList = localDataSource.getTvShows()
            if (tvShowsList.isNotEmpty()) {
                return tvShowsList
            } else {
                tvShowsList = getTvShowsFromRemote()
                localDataSource.saveTvShowsToDB(tvShowsList)
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return tvShowsList
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowsList: List<TvShow>
        try {
            tvShowsList = cachedDataSource.getTvShowsFromCache()
            if (tvShowsList.isNotEmpty()) {
                return tvShowsList
            } else {
                tvShowsList = getTvShowsFromLocal()
                cachedDataSource.saveTvShowsToCache(tvShowsList)
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return tvShowsList
    }
}
