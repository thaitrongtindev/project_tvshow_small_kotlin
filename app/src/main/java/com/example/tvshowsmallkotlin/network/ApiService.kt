package com.example.tvshowsmallkotlin.network

import retrofit2.http.GET

interface ApiService {


    //https://www.episodate.com/api/most-popular
    //https://www.episodate.com/api/most-popular?page=1

    @GET("most-popular")
    suspend fun
}