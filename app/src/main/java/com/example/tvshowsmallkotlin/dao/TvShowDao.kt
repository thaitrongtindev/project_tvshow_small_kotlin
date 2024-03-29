package com.example.tvshowsmallkotlin.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvshowsmallkotlin.models.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShows")
    fun getWatchlist():MutableList<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchlist(tvShow: TvShow)

    @Delete
    suspend fun removeFromWatchlist(tvShow: TvShow)
}