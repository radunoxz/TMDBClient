package com.example.tmdbclient.domain.usecase.tvshow


import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository
import io.reactivex.Observable

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    fun execute(): Observable<List<TvShow>> = tvShowRepository.updateTvShows()
}
