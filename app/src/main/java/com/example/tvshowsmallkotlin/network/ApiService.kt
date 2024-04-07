package com.example.tvshowsmallkotlin.network

import com.example.tvshowsmallkotlin.responses.TVShowResponse
import com.example.tvshowsmallkotlin.responses.TvShowDetailsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    //https://www.episodate.com/api/most-popular
    //https://www.episodate.com/api/most-popular?page=1

    @GET("most-popular")
   suspend fun getMostPopularTVShows(@Query("page") page: Int) : Response<TVShowResponse>
    //    //https://www.episodate.com/api/show-details?q=arrow
    @GET("show-details")
    suspend  fun getTvShowDetails(@Query("q") tvShowId: String) : Response<TvShowDetailsResponse>

    @GET("search")
    suspend fun searchTvShows(@Query("q") query: String, @Query("page") page: Int):Response<TVShowResponse>
}