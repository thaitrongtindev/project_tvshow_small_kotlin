package com.example.tvshowsmallkotlin.responses

import com.example.tvshowsmallkotlin.models.TvShow
import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("page")
    private var page: Int? = null,

    @com.google.gson.annotations.SerializedName("pages")
    private var totalPages: Int? = null,

    @SerializedName("tv_shows")
    private val tvShows: List<TvShow>? = null

)