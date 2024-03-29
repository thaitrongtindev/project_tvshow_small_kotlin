package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName

data class TVShowDetails(
    @SerializedName("url")
    var url: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("runtime")
    var runtime: String? = null,

    @SerializedName("image_path")
    var imagePath: String? = null,

    @SerializedName("rating")
    var rating: String? = null,

    @SerializedName("genres")
    var genres: Array<String>,

    @SerializedName("pictures")
    var pictures: Array<String>,

    @SerializedName("episodes")
    var episodes: List<Episode>? = null

)
