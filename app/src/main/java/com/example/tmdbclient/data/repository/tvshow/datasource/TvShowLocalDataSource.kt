package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tv.TvShow
import io.reactivex.Flowable

interface TvShowLocalDataSource {
    fun getTvShows(): Flowable<List<TvShow>>
    fun saveTvShowsToDB(tvShows: List<TvShow>)
    fun clearAll()
}
