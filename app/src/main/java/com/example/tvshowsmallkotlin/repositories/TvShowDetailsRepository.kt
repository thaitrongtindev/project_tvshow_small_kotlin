package com.example.tvshowsmallkotlin.repositories

import ApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsmallkotlin.network.ApiService
import com.example.tvshowsmallkotlin.responses.TvShowDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailsRepository {

    val apiService = ApiClient().getRetrofitInstance().create(ApiService::class.java)
    suspend fun getTvShowDetails(tvShowId: String): LiveData<TvShowDetailsResponse> {
        return withContext(Dispatchers.IO) {
            val data = MutableLiveData<TvShowDetailsResponse>()
            try {
                val response = apiService.getTvShowDetails(tvShowId)
                if (response.isSuccessful) {
                    data.postValue(response.body())
                } else {
                    // Handle unsuccessful response
                    Log.e("TvShowDetailsRepository", "getTvShowDetails: Unsuccessful response")
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("TvShowDetailsRepository", "getTvShowDetails: Error - ${e.message}")
            }
            data
        }
    }
}