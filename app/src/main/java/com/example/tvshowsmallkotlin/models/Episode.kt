package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName

data class Episode(

    @SerializedName("season")
    private val season: String? = null,

    @SerializedName("episode")
    private var episode: String? = null,

    @SerializedName("name")
    private var name: String? = null,

    @SerializedName("air_date")
    private val airDate: String? = null

)
