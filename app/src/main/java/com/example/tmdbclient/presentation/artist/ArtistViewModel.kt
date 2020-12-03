package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.artist.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase

class ArtistViewModel(
    private val getArtistUseCase: GetArtistUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {
    fun getArtists() = liveData {
        val artistList = getArtistUseCase.execute()
        emit(artistList)
    }

    fun updateArtists() = liveData {
        val artistList = updateArtistsUseCase.execute()
        emit(artistList)
    }
}
