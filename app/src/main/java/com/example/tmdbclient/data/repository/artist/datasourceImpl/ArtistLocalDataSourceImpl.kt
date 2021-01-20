package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * This class is a implementation of the Local Data Source
 */
class ArtistLocalDataSourceImpl
@Inject constructor(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override fun getArtists(): Flowable<List<Artist>> = artistDao.getArtists()

    override fun saveArtistToDB(artists: List<Artist>) {
        artistDao.insertArtists(artists)
    }

    override fun clearAll() {
        artistDao.deleteAllArtists()
    }
}
