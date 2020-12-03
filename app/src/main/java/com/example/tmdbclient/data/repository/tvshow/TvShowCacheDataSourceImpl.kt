package com.example.tmdbclient.data.repository.tvshow

import com.example.tmdbclient.data.model.tv.TvShow

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
