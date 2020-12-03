package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource

/**
 * This class is a implementation of the Cache Data Source
 */
class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistsList = ArrayList<Artist>()

    override suspend fun getArtistFromCache(): List<Artist> = artistsList

    override suspend fun saveArtistToCache(artists: List<Artist>) {
        artistsList.clear()
        artistsList = ArrayList(artists)
    }
}
