package com.example.tmdbclient.domain.usecase.tvshow

import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This usecase is used to retrieve a list of tvshows from [TvShowRepository]
 * and it should be used in the correspondent ViewModel
 */
class GetTvShowsUseCase
@Inject constructor(private val tvShowRepository: TvShowRepository) {
    fun execute(): Observable<List<TvShow>> =
        tvShowRepository.getTvShows().take(1).flatMapIterable { it }.toList().toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
