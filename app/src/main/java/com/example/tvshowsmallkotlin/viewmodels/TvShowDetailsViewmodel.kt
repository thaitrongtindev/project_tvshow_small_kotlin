package com.example.tvshowsmallkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsmallkotlin.dao.TvShowDatabase
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.repositories.TvShowDetailsRepository
import com.example.tvshowsmallkotlin.responses.TvShowDetailsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowDetailsViewmodel(private val repository: TvShowDetailsRepository, private val tvShowDatabase: TvShowDatabase) : ViewModel() {
    suspend fun getTvShowDetails(tvShowId: String): LiveData<TvShowDetailsResponse> {
        return repository.getTvShowDetails(tvShowId)
    }

    fun addWatchlist(tvShow: TvShow) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tvShowDatabase.tvShowDao().addToWatchlist(tvShow)
            }
        }
    }

    fun removeTvShowFromWatchlist(tvShow: TvShow) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tvShowDatabase.tvShowDao().removeFromWatchlist(tvShow)
            }
        }
    }
    fun getTvShowFromWatchlist(tvShowId : String) : LiveData<TvShow> {
        return tvShowDatabase.tvShowDao().getTvShowFromWatchlist(tvShowId)
    }


}