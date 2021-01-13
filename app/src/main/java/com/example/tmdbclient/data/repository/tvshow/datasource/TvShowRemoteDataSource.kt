package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tv.TvShowList
import io.reactivex.Observable

interface TvShowRemoteDataSource {
    fun getTvShows(): Observable<TvShowList>
}
