package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

/**
 * This class is a implementation of the Cache Data Source
 */
class TvShowCacheDataSourceImpl : TvShowCacheDataSource {
    private var tvShowsList = ArrayList<TvShow>()
    override suspend fun getTvShowsFromCache(): List<TvShow> = tvShowsList

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowsList.clear()
        tvShowsList = ArrayList(tvShows)
    }
}
