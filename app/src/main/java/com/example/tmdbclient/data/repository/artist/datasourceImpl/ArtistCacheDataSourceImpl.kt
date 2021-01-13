package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import io.reactivex.Flowable

/**
 * This class is a implementation of the Cache Data Source
 */
class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistsList: MutableList<Artist> = ArrayList()

    override fun getArtistFromCache(): Flowable<List<Artist>> = Flowable.just(artistsList)

    override fun saveArtistToCache(artists: List<Artist>) {
        artistsList.clear()
        artistsList.addAll(artists)
    }
}
