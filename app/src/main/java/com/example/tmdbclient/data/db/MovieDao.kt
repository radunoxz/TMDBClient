package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM popular_movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM popular_movies")
    fun getMovies(): Flowable<List<Movie>>
}
