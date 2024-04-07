package com.example.tvshowsmallkotlin.repositories

import ApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsmallkotlin.network.ApiService
import com.example.tvshowsmallkotlin.responses.TVShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchTvShowRepository {
        private val apiService = ApiClient().getRetrofitInstance().create(ApiService::class.java)
    suspend fun searchTvShow(query: String, page: Int) : LiveData<TVShowResponse> {
        return withContext(Dispatchers.IO) {
            val data = MutableLiveData<TVShowResponse>()
            try {
                val response = apiService.searchTvShows(query,page)
                if (response.isSuccessful) {
                    data.postValue(response.body())
                } else {
                    // Handle unsuccessful response
                    Log.e("Search TvShow", "getTvShowDetails: Unsuccessful response")
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("Search TvShow", "getTvShowDetails: Error - ${e.message}")
            }
            data

        }
    }



}