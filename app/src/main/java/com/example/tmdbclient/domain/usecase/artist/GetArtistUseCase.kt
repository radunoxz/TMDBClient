package com.example.tmdbclient.domain.usecase.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetArtistUseCase(private val artistRepository: ArtistRepository) {
    fun execute(): Observable<List<Artist>> =
        artistRepository.getArtists().take(1).flatMapIterable { it }.toList().toObservable()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
