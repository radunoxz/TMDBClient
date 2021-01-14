package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Provides an implementation of the [ArtistRepository] interface for communicating to and from
 * data sources.
 */
class ArtistRepositoryImpl(
    private val remoteDataSource: ArtistRemoteDataSource,
    private val localDataSource: ArtistLocalDataSource,
    private val cacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override fun getArtists(): Observable<List<Artist>> = getArtistsFromCache().toObservable()

    override fun updateArtists(): Observable<List<Artist>> = getArtistsFromAPI().doOnNext {
        localDataSource.clearAll()
        localDataSource.saveArtistToDB(it)
        cacheDataSource.saveArtistToCache(it)
    }

    private fun getArtistsFromAPI(): Observable<List<Artist>> =
        remoteDataSource.getArtists().take(1).doOnNext {
            Log.i("MYTAG", Thread.currentThread().id.toString())
        }.map { list ->
            list.artists
        }

    private fun getArtistFromDataBase() =
        localDataSource.getArtists().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getArtistsFromAPI().map {
                localDataSource.saveArtistToDB(it)
                it
            }.toFlowable(BackpressureStrategy.ERROR)
        )

    private fun getArtistsFromCache() =
        cacheDataSource.getArtistFromCache().take(1).flatMap {
            if (it.isNotEmpty()) {
                Flowable.just(it)
            } else {
                Flowable.empty()
            }
        }.switchIfEmpty(
            getArtistFromDataBase().map {
                cacheDataSource.saveArtistToCache(it)
                it
            })
}
