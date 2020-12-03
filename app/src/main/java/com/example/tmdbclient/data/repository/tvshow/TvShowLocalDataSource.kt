package com.example.tmdbclient.data.repository.tvshow

import com.example.tmdbclient.data.model.tv.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShows(): List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearAll()
}
