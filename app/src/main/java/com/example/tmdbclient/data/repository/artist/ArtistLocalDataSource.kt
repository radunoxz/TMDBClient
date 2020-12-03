package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtists(): List<Artist>
    suspend fun saveArtistToDB(artists: List<Artist>)
    suspend fun clearAll()
}
