package com.example.tmdbclient.presentation.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.tmdbclient.domain.usecase.tvshow.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.tvshow.UpdateTvShowsUseCase

class TvShowViewModel
@ViewModelInject constructor(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {

    fun getTvShows() = getTvShowsUseCase.execute()

    fun updateTvShows() = updateTvShowsUseCase.execute()
}
