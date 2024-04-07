package com.example.tvshowsmallkotlin.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsmallkotlin.dao.TvShowDatabase
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.responses.TVShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchlistViewmodel(application: Application) : AndroidViewModel(application) {
    var tvShowDatabase: TvShowDatabase? = TvShowDatabase.getInstance(application)

    fun loadWatchlist() : MutableList<TvShow> {
        return tvShowDatabase!!.tvShowDao().getWatchlist()
    }

    suspend fun removeFromWatchlist(tvShow: TvShow) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tvShowDatabase!!.tvShowDao().removeFromWatchlist(tvShow)
            }
        }
    }
//
//     suspend fun searchTvShowFromWatchlist(queryName: String) :TvShow {
//        return tvShowDatabase!!.tvShowDao().searchTvShowFromWatchlist(queryName)
//    }
//     suspend fun searchTvShowFromWatchlist(queryName: String): LiveData<TvShow?> {
//        val searchData = MutableLiveData<TvShow?>()
//        try {
//            val tvShow = tvShowDatabase!!.tvShowDao().searchTvShowFromWatchlist(queryName)
//            searchData.postValue(tvShow)
//        } catch (e: Exception) {
//            Log.e("WatchlistViewModel", "Error searching TV show from watchlist: ${e.message}")
//        }
//        return searchData
//    }


    fun searchTvShowByName(showName: String): LiveData<List<TvShow>> {
        return tvShowDatabase!!.tvShowDao().getTvShowByName(showName)
    }




}