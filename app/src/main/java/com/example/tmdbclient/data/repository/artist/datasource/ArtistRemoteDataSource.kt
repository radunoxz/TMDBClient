package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.ArtistList
import io.reactivex.Observable

interface ArtistRemoteDataSource {
     fun getArtists(): Observable<ArtistList>
}
