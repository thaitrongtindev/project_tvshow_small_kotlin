package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName

data class Episode(

    @SerializedName("season")
    var season: String? = null,

    @SerializedName("episode")
     var episode: String? = null,

    @SerializedName("name")
     var name: String? = null,

    @SerializedName("air_date")
    var airDate: String? = null

)
