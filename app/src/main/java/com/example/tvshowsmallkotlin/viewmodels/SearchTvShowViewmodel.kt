package com.example.tvshowsmallkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsmallkotlin.repositories.SearchTvShowRepository
import com.example.tvshowsmallkotlin.responses.TVShowResponse

class SearchTvShowViewmodel()  : ViewModel(){
    private var repository: SearchTvShowRepository = SearchTvShowRepository()
    suspend fun searchTvShow(query: String, page: Int) : LiveData<TVShowResponse> {
        return repository.searchTvShow(query,page)
    }
}