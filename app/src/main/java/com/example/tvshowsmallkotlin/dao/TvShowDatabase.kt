package com.example.tvshowsmallkotlin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvshowsmallkotlin.models.TvShow

@Database(entities = [TvShow::class], version = 1, exportSchema = true)
abstract class TvShowDatabase() : RoomDatabase() {

    abstract fun tvShowDao() : TvShowDao
    companion object {
        @Volatile
        var INSTANCE : TvShowDatabase? = null
        @Synchronized
        fun getInstance(context: Context) : TvShowDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, TvShowDatabase::class.java,
                    "tvShow.db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE as TvShowDatabase
        }
    }
}