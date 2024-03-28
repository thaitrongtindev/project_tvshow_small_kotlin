package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName

data class TVShowDetails(
    @SerializedName("url")
     var url: String? = null,

    @SerializedName("description")
     val description: String? = null,

    @SerializedName("runtime")
     val runtime: String? = null,

    @SerializedName("image_path")
     val imagePath: String? = null,

    @SerializedName("rating")
     val rating: String? = null,

    @SerializedName("genres")
     val genres: Array<String>,

    @SerializedName("pictures")
     val pictures: Array<String>,

    @SerializedName("episodes")
     val episodes: List<Episode>? = null

)
