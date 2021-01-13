package com.example.tmdbclient.domain.usecase.tvshow

import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    fun execute(): Observable<List<TvShow>> =
        tvShowRepository.getTvShows().take(1).flatMapIterable { it }.toList().toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
