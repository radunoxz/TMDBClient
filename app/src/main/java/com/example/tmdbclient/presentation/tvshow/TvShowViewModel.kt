package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.tvshow.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.tvshow.UpdateTvShowsUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {

    fun getTvShows() = getTvShowsUseCase.execute()

    fun updateTvShows() = updateTvShowsUseCase.execute()
}
