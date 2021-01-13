package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artist.Artist
import io.reactivex.Observable

interface ArtistRepository {
     fun getArtists(): Observable<List<Artist>>
     fun updateArtists(): Observable<List<Artist>>
}
