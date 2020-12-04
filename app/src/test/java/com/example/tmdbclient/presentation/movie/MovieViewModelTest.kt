package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        movieViewModel = MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()

        movies.add(
            Movie(
                1,
                "overview1",
                "posterpath1",
                releaseDate = "releasedate1",
                title = "title1"
            )
        )
        movies.add(
            Movie(
                2,
                "overview2",
                "posterpath2",
                releaseDate = "releasedate2",
                title = "title2"
            )
        )
        movies.add(
            Movie(
                3,
                "overview3",
                "posterpath3",
                releaseDate = "releasedate3",
                title = "title3"
            )
        )

        val currentList = movieViewModel.getMovies().getOrAwaitValue()
        assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()

        movies.add(
            Movie(
                4,
                "overview1",
                "posterpath1",
                releaseDate = "releasedate1",
                title = "title1"
            )
        )
        movies.add(
            Movie(
                5,
                "overview2",
                "posterpath2",
                releaseDate = "releasedate2",
                title = "title2"
            )
        )
        movies.add(
            Movie(
                6,
                "overview3",
                "posterpath3",
                releaseDate = "releasedate3",
                title = "title3"
            )
        )

        val updatedList = movieViewModel.updateMovies().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(movies)
    }
}


