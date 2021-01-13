package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.artist.Artist
import io.reactivex.Flowable

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_artists")
    fun deleteAllArtists()

    @Query("SELECT * FROM popular_artists")
    fun getArtists(): Flowable<List<Artist>>
}
