package com.example.tmdbclient.domain.usecase.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This usecase is used to retrieve a list of artists from [ArtistRepository]
 * and it should be used in the correspondent ViewModel
 */
class UpdateArtistsUseCase
@Inject constructor(private val artistRepository: ArtistRepository) {
    fun execute(): Observable<List<Artist>> = artistRepository.updateArtists()
}
