package com.example.tvshowsmallkotlin.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "tvShows")
data class TvShow(
    @PrimaryKey
    @SerializedName("id")
     var id: Int? = null,

    @com.google.gson.annotations.SerializedName("name")
     val name: String? = null,

    @SerializedName("start_date")
     val startDate: String? = null,

    @SerializedName("country")
     val country: String? = null,

    @SerializedName("network")
     val network: String? = null,

    @SerializedName("status")
     val status: String? = null,

    @SerializedName("image_thumbnail_path")
     val thumbnail: String? = null
) :Serializable