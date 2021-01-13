package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import io.reactivex.Flowable

/**
 * This class is a implementation of the Cache Data Source
 */
class TvShowCacheDataSourceImpl : TvShowCacheDataSource {
    private var tvShowsList: MutableList<TvShow> = ArrayList()
    override fun getTvShowsFromCache(): Flowable<List<TvShow>> = Flowable.just(tvShowsList)

    override fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowsList.clear()
        tvShowsList.addAll(tvShows)
    }
}
