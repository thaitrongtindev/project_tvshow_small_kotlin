package com.example.tvshowsmallkotlin.models

import com.google.gson.annotations.SerializedName


data class TVShow(

    @SerializedName("id")
    private val id: Int? = null,

    @SerializedName("name")
    private val name: String? = null,

    @SerializedName("start_date")
    private val startDate: String? = null,

    @SerializedName("country")
    private val country: String? = null,

    @SerializedName("network")
    private val network: String? = null,

    @SerializedName("status")
    private val status:String? = null,

    @SerializedName("image_thumbnail_path")
    private val thumbnail: String? = null


)
