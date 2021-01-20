package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Provides an implementation of the [TvShowRepository] interface for communicating to and from
 * data sources.
 */
class TvShowRepositoryImpl
@Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource,
    private val localDataSource: TvShowLocalDataSource,
    private val cachedDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override fun getTvShows(): Observable<List<TvShow>> =
        getTvShowsFromCache().toObservable()

    override fun updateTvShows(): Observable<List<TvShow>> =
        getTvShowsFromAPI().doOnNext {
            localDataSource.clearAll()
            localDataSource.saveTvShowsToDB(it)
            cachedDataSource.saveTvShowsToCache(it)
        }

    private fun getTvShowsFromAPI(): Observable<List<TvShow>> =
        remoteDataSource.getTvShows().doOnNext {
            Log.e("MYTAG", Thread.currentThread().id.toString())
        }.map { list ->
            list.tvShows
        }

    private fun getTvShowsFromDatabase(): Flowable<List<TvShow>> {
        Log.i("MYTAG", "getTvShowsFromDatabase")
        return localDataSource.getTvShows().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getTvShowsFromAPI().map {
                localDataSource.saveTvShowsToDB(it)
                it
            }.toFlowable(BackpressureStrategy.ERROR)
        )
    }

    private fun getTvShowsFromCache(): Flowable<List<TvShow>> {
        Log.i("MYTAG", "getTvShowsFromCache")
        return cachedDataSource.getTvShowsFromCache().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getTvShowsFromDatabase().map {
                cachedDataSource.saveTvShowsToCache(it)
                it
            })
    }
}
