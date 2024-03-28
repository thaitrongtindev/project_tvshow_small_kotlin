package com.example.tvshowsmallkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsmallkotlin.repositories.TvShowDetailsRepository
import com.example.tvshowsmallkotlin.responses.TvShowDetailsResponse

class TvShowDetailsViewmodel(private val repository: TvShowDetailsRepository) : ViewModel() {
    suspend fun getTvShowDetails(tvShowId: String): LiveData<TvShowDetailsResponse> {
        return repository.getTvShowDetails(tvShowId)
    }

}