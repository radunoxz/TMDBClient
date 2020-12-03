package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.domain.repository.ArtistRepository
import java.lang.Exception

class ArtistRepositoryImpl(
    private val remoteDataSourceImpl: ArtistRemoteDataSourceImpl,
    private val localDataSourceImpl: ArtistLocalDataSourceImpl,
    private val cacheDataSourceImpl: ArtistCacheDataSourceImpl
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? = getArtistsFromCache()

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists = getArtistsFromRemote()
        localDataSourceImpl.clearAll()
        localDataSourceImpl.saveArtistToDB(newListOfArtists)
        cacheDataSourceImpl.saveArtistToCache(newListOfArtists)

        return newListOfArtists
    }

    private suspend fun getArtistsFromRemote(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            val response = remoteDataSourceImpl.getArtists()
            val body = response.body()
            if (body != null) {
                artistsList = body.artists
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return artistsList
    }

    private suspend fun getArtistsFromLocal(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList = localDataSourceImpl.getArtists()
            if (artistsList.isNotEmpty()) {
                return artistsList
            } else {
                artistsList = getArtistsFromRemote()
                localDataSourceImpl.saveArtistToDB(artistsList)
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return artistsList
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistsList: List<Artist>
        try {
            artistsList = cacheDataSourceImpl.getArtistFromCache()
            if (artistsList.isNotEmpty()) {
                return artistsList
            } else {
                artistsList = getArtistsFromLocal()
                cacheDataSourceImpl.saveArtistToCache(artistsList)
            }
        } catch (exception: Exception) {
            Log.e("MYTAG", exception.message.toString())
        }

        return artistsList
    }

}
