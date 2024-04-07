package com.example.tvshowsmallkotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.responses.TVShowResponse

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShows")
    fun getWatchlist():MutableList<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchlist(tvShow: TvShow)

    @Delete
    suspend fun removeFromWatchlist(tvShow: TvShow)

    @Query("SELECT * FROM tvShows WHERE id = :tvShowId")
    fun getTvShowFromWatchlist(tvShowId: String):LiveData<TvShow>

//    @Query("SELECT name FROM tvShows WHERE name = :strName")
//    suspend fun searchTvShowFromWatchlist(strName: String): TvShow

    @Query("SELECT * FROM tvShows WHERE LOWER(name) LIKE LOWER(:showName)")
    fun getTvShowByName(showName: String): LiveData<List<TvShow>>


}