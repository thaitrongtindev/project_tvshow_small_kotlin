package com.example.tvshowsmallkotlin.network

import com.example.tvshowsmallkotlin.responses.TVShowResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    //https://www.episodate.com/api/most-popular
    //https://www.episodate.com/api/most-popular?page=1

    @GET("most-popular")
    fun getMostPopularTVShows(@Query("page") page: Int) : Call<TVShowResponse>
}