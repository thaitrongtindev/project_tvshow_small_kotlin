package com.example.tvshowsmallkotlin.responses

import com.example.tvshowsmallkotlin.models.TVShow
import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("page")
    private var page: Int? = null,

    @SerializedName("pages")
    private val totalPages: Int? = null,

    @SerializedName("tv_shows")
    private val tvShows: List<TVShow>? = null
)
