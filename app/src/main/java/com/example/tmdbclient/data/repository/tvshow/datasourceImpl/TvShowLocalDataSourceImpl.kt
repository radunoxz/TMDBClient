package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * This class is a implementation of the Local Data Source
 */
class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao
) : TvShowLocalDataSource {
    override suspend fun getTvShows(): List<TvShow> =
        tvShowDao.getTvShows()


    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(IO).launch {
            tvShowDao.insertTvShoes(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}
