package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.review.Review
import com.example.tmdbclient.data.model.tv.TvShowList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<ArtistList>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey: String
    ): Observable<Review>
}
