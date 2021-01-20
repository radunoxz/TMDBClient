package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.tv.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * This class is a implementation of the Local Data Source
 */
class TvShowLocalDataSourceImpl
@Inject constructor(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {
    override fun getTvShows(): Flowable<List<TvShow>> =
        tvShowDao.getTvShows()

    override fun saveTvShowsToDB(tvShows: List<TvShow>) {
        tvShowDao.insertTvShoes(tvShows)
    }

    override fun clearAll() {
        tvShowDao.deleteAllTvShows()
    }
}
