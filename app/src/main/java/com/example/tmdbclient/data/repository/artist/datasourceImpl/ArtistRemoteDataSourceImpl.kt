package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This class is a implementation of the Remote Data Source
 */
class ArtistRemoteDataSourceImpl
@Inject constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ArtistRemoteDataSource {
    override fun getArtists(): Observable<ArtistList> =
        tmdbService.getPopularArtists(apiKey)
}
