package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.tv.TvShow
import io.reactivex.Observable

interface TvShowRepository {
    fun getTvShows(): Observable<List<TvShow>>
    fun updateTvShows(): Observable<List<TvShow>>
}
