package com.example.tvshowsmallkotlin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowsmallkotlin.repositories.TvShowDetailsRepository

class TvShowDetailsFactory(private val repository: TvShowDetailsRepository) :ViewModelProvider.Factory {
    @Override
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowDetailsViewmodel::class.java)) {
            return TvShowDetailsViewmodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

