package com.data.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.model.movie.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: MovieDao
    private lateinit var database: TMDBDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        dao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveAndGetMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overview1", "posterpath1", releaseDate = "releasedate1", title = "title1"),
            Movie(2, "overview2", "posterpath2", releaseDate = "releasedate2", title = "title2"),
            Movie(3, "overview3", "posterpath3", releaseDate = "releasedate3", title = "title3")
        )

        dao.saveMovies(movies)

        val allMovies = dao.getMovies()

        assertThat(allMovies).isEqualTo(movies)
    }

    @Test
    fun deleteMoviesTest() = runBlocking{
        val movies = listOf(
            Movie(1, "overview1", "posterpath1", releaseDate = "releasedate1", title = "title1"),
            Movie(2, "overview2", "posterpath2", releaseDate = "releasedate2", title = "title2"),
            Movie(3, "overview3", "posterpath3", releaseDate = "releasedate3", title = "title3")
        )
        dao.saveMovies(movies)
        dao.deleteAllMovies()

        val movieResult= dao.getMovies()
        assertThat(movieResult).isEmpty()
    }
}
