package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.tv.TvShow
import io.reactivex.Observable

/**
 * Interface defining methods for how the data layer can pass TvShow data to and from the Domain layer.
 * This is to be implemented by the domain layer, setting the requirements for the
 * operations that need to be implemented
 */
interface TvShowRepository {
    fun getTvShows(): Observable<List<TvShow>>

    fun updateTvShows(): Observable<List<TvShow>>
}
