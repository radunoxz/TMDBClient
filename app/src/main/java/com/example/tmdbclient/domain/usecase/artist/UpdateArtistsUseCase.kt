package com.example.tmdbclient.domain.usecase.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository
import io.reactivex.Observable

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    fun execute(): Observable<List<Artist>> = artistRepository.updateArtists()
}
