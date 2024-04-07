package com.example.tvshowsmallkotlin.responses

import com.example.tvshowsmallkotlin.models.TVShowDetails
import com.google.gson.annotations.SerializedName

data class TvShowDetailsResponse(


   @SerializedName("tvShow")
     var tvShowDetails: TVShowDetails


)
