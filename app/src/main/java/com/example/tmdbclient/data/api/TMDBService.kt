package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.review.Review
import com.example.tmdbclient.data.model.tv.TvShowList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieList>

    @GET("tv/popular")
    fun getPopularTvShows(@Query("api_key") apiKey: String): Observable<TvShowList>

    @GET("person/popular")
    fun getPopularArtists(@Query("api_key") apiKey: String): Observable<ArtistList>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Observable<Review>
}
