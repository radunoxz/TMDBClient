package com.example.tmdbclient.domain.usecase.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This usecase is used to retrieve a list of artists from [ArtistRepository]
 * and it should be used in the correspondent ViewModel
 */
class GetArtistUseCase
@Inject constructor(private val artistRepository: ArtistRepository) {
    fun execute(): Observable<List<Artist>> =
        artistRepository.getArtists().take(1).flatMapIterable { it }.toList().toObservable()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
