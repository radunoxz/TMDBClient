package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val remoteDataSource: ArtistRemoteDataSource,
    private val localDataSource: ArtistLocalDataSource,
    private val cacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? = getArtistsFromCache()

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists = getArtistsFromRemote()
        localDataSource.clearAll()
        localDataSource.saveArtistToDB(newListOfArtists)
        cacheDataSource.saveArtistToCache(newListOfArtists)

        return newListOfArtists
    }

    private suspend fun getArtistsFromRemote(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            val response = remoteDataSource.getArtists()
            val body = response.body()
            if (body != null) {
                artistsList = body.artists
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", "${exception.message.toString()} 1")
        }

        return artistsList
    }

    private suspend fun getArtistsFromLocal(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList = localDataSource.getArtists()
            if (artistsList.isNotEmpty()) {
                return artistsList
            } else {
                artistsList = getArtistsFromRemote()
                localDataSource.saveArtistToDB(artistsList)
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", "${exception.message.toString()} 1")
        }

        return artistsList
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList = cacheDataSource.getArtistFromCache()

        } catch (exception: Exception) {
            Log.e("MYTAG", "${exception.message.toString()} 1")
        }
        if (artistsList.isNotEmpty()) {
            return artistsList
        } else {
            artistsList = getArtistsFromLocal()
            cacheDataSource.saveArtistToCache(artistsList)
        }

        return artistsList
    }

}
