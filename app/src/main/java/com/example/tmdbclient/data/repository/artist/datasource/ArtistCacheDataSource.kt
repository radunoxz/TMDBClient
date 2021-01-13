package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist
import io.reactivex.Flowable

interface ArtistCacheDataSource {
    fun getArtistFromCache(): Flowable<List<Artist>>
    fun saveArtistToCache(artists: List<Artist>)
}
