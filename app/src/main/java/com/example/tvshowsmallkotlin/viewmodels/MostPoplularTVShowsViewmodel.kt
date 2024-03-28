package com.example.tvshowsmallkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsmallkotlin.repositories.MostPopularTVShowRepository
import com.example.tvshowsmallkotlin.responses.TVShowResponse

class MostPoplularTVShowsViewmodel : ViewModel() {
    private lateinit var repository : MostPopularTVShowRepository
    init {
        repository = MostPopularTVShowRepository()
    }

    suspend fun getMostPopularTVShows(page: Int) : LiveData<TVShowResponse> {
        return repository.getMostPopularTVShows(page)
    }
}