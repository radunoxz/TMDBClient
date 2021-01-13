package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist
import io.reactivex.Flowable

interface ArtistLocalDataSource {
    fun getArtists(): Flowable<List<Artist>>
    fun saveArtistToDB(artists: List<Artist>)
    fun clearAll()
}
