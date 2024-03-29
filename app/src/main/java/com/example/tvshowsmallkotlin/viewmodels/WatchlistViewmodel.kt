package com.example.tvshowsmallkotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsmallkotlin.dao.TvShowDatabase
import com.example.tvshowsmallkotlin.models.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchlistViewmodel(application: Application) : AndroidViewModel(application) {
    var tvShowDatabase: TvShowDatabase? = null
    init {
         tvShowDatabase = TvShowDatabase.getInstance(application)
    }
    fun loadWatchlist() : List<TvShow> {
        return tvShowDatabase!!.tvShowDao().getWatchlist()
    }

    suspend fun removeFromWatchlist(tvShow: TvShow) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tvShowDatabase!!.tvShowDao().removeFromWatchlist(tvShow)
            }
        }
    }




}