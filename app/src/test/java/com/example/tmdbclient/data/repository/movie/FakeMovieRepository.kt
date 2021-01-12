package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.review.Review
import com.example.tmdbclient.domain.repository.MovieRepository
import io.reactivex.Observable

class FakeMovieRepository : MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        movies.add(
            Movie(
                1,
                "overview1",
                "posterpath1",
                releaseDate = "releasedate1",
                title = "title1",
                review = "blablabla",
                voteAverage = 6.7f
            )
        )
        movies.add(
            Movie(
                2,
                "overview2",
                "posterpath2",
                releaseDate = "releasedate2",
                title = "title2",
                review = "blablabla",
                voteAverage = 6.7f
            )
        )
        movies.add(
            Movie(
                3,
                "overview3",
                "posterpath3",
                releaseDate = "releasedate3",
                title = "title3",
                review = "blablabla",
                voteAverage = 6.7f
            )
        )

    }

    override fun getMovies(): Observable<List<Movie>> {
        return Observable.fromArray(movies)
    }

    override fun updateMovies(): Observable<List<Movie>> {
        movies.clear()
        movies.add(
            Movie(
                4,
                "overview1",
                "posterpath1",
                releaseDate = "releasedate1",
                title = "title1",
                review = "blablabla",
                voteAverage = 2.1f
            )
        )
        movies.add(
            Movie(
                5,
                "overview2",
                "posterpath2",
                releaseDate = "releasedate2",
                title = "title2",
                review = "blablabla",
                voteAverage = 2.1f
            )
        )
        movies.add(
            Movie(
                6,
                "overview3",
                "posterpath3",
                releaseDate = "releasedate3",
                title = "title3",
                review = "blablabla",
                voteAverage = 2.1f
            )
        )

        return Observable.fromArray(movies)
    }

//    override suspend fun getMovies(): List<Movie> {
//        return movies
//    }
//
//    override suspend fun updateMovies(): List<Movie> {
//        movies.clear()
//        movies.add(
//            Movie(
//                4,
//                "overview1",
//                "posterpath1",
//                releaseDate = "releasedate1",
//                title = "title1"
//            )
//        )
//        movies.add(
//            Movie(
//                5,
//                "overview2",
//                "posterpath2",
//                releaseDate = "releasedate2",
//                title = "title2"
//            )
//        )
//        movies.add(
//            Movie(
//                6,
//                "overview3",
//                "posterpath3",
//                releaseDate = "releasedate3",
//                title = "title3"
//            )
//        )
//
//        return movies
//
//    }

    override fun getReviews(movieId: String): Observable<Review> {
        TODO("Not yet implemented")
    }
}
