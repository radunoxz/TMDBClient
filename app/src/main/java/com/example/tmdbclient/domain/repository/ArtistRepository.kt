package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artist.Artist
import io.reactivex.Observable

/**
 * Interface defining methods for how the data layer can pass Artist data to and from the Domain layer.
 * This is to be implemented by the domain layer, setting the requirements for the
 * operations that need to be implemented
 */

interface ArtistRepository {
    fun getArtists(): Observable<List<Artist>>

    fun updateArtists(): Observable<List<Artist>>
}
