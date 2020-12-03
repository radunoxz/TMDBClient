package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.model.artist.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * This class is a implementation of the Local Data Source
 */
class ArtistLocalDataSourceImpl(
    private val artistDao: ArtistDao
) : ArtistLocalDataSource {
    override suspend fun getArtists(): List<Artist> = artistDao.getArtists()

    override suspend fun saveArtistToDB(artists: List<Artist>) {
        CoroutineScope(IO).launch {
            artistDao.insertArtists(artists)
        }

    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch {
            artistDao.deleteAllArtists()
        }

    }
}
