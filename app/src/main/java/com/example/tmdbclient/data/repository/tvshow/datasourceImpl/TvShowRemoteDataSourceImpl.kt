package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tv.TvShowList
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This class is a implementation of the Remote Data Source
 */
class TvShowRemoteDataSourceImpl
@Inject constructor(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TvShowRemoteDataSource {
    override fun getTvShows(): Observable<TvShowList> =
        tmdbService.getPopularTvShows(apiKey)
}
