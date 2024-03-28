package com.example.tvshowsmallkotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    // https://www.episodate.com/api
    // https://www.episodate.com/api/most-popular?page=1

    val URL =  "https://www.episodate.com/api"

    var retrofit: Retrofit? = null
    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}