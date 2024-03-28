package com.example.tvshowsmallkotlin.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsmallkotlin.network.ApiClient
import com.example.tvshowsmallkotlin.network.ApiService
import com.example.tvshowsmallkotlin.responses.TVShowResponse

class MostPopularTVShowRepository {
    private val apiService : ApiService = ApiClient().getRetrofit().create(ApiService::class.java)


    suspend fun getMostPopularTVShows(page: Int): LiveData<TVShowResponse> {
        val data = MutableLiveData<TVShowResponse>()
        try {
            val response = apiService.getMostPopularTVShows(page)
            data.value = response.body()
        } catch (e: Exception) {
            // Handle error
            Log.e("TAG", "getMostPopularTVShows: " + "Error" )
        }
        return data
    }
}