package com.example.tmdbclient.data.repository.tvshow

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tv.TvShowList
import retrofit2.Response

/**
 * This class is a implementation of the Remote Data Source
 */
class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
):TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> =
        tmdbService.getPopularTvShows(apiKey)
}
