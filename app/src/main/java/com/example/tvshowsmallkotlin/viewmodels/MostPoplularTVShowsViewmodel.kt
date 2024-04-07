package com.example.tvshowsmallkotlin.viewmodels

import MostPopularTVShowRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsmallkotlin.responses.TVShowResponse

class MostPoplularTVShowsViewmodel : ViewModel() {
    private var repository : MostPopularTVShowRepository = MostPopularTVShowRepository()

    suspend fun getMostPopularTVShows(page: Int) : LiveData<TVShowResponse> {
            return repository.getMostPopularTVShows(page)
    }
}