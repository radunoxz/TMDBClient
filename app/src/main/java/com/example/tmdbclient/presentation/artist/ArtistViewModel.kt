package com.example.tmdbclient.presentation.artist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase

class ArtistViewModel
@ViewModelInject constructor(
    private val getArtistUseCase: GetArtistUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {
    fun getArtists() = getArtistUseCase.execute()

    fun updateArtists() = updateArtistsUseCase.execute()
}
