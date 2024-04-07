package com.example.tvshowsmallkotlin.listeners

import com.example.tvshowsmallkotlin.models.TvShow

interface WatchlistListener {

     fun onTvShowClicked(tvShow: TvShow)
     fun onDeleteTvShow(tvShow: TvShow, position: Int)
}