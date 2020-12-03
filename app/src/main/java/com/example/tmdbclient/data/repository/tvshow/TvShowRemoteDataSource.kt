package com.example.tmdbclient.data.repository.tvshow

import com.example.tmdbclient.data.model.tv.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}
