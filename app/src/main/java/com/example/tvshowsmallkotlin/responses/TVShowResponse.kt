package com.example.tvshowsmallkotlin.responses

import com.example.tvshowsmallkotlin.models.TvShow
import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("page")
     var page: Int? = null,

    @SerializedName("pages")
     var totalPages: Int? = null,

    @SerializedName("tv_shows")
     var tvShows: List<TvShow>? = null

)