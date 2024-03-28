package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName

data class TVShowDetails(
    @SerializedName("url")
    private var url: String? = null,

    @SerializedName("description")
    private val description: String? = null,

    @SerializedName("runtime")
    private val runtime: String? = null,

    @SerializedName("image_path")
    private val imagePath: String? = null,

    @SerializedName("rating")
    private val rating: String? = null,

    @SerializedName("genres")
    private val genres: Array<String>,

    @SerializedName("pictures")
    private val pictures: Array<String>,

    @SerializedName("episodes")
    private val episodes: List<Episode>? = null

)
