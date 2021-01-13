package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tv.TvShow
import io.reactivex.Flowable

interface TvShowCacheDataSource {
     fun getTvShowsFromCache(): Flowable<List<TvShow>>
     fun saveTvShowsToCache(tvShows: List<TvShow>)
}
