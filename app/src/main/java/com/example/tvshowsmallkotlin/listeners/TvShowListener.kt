package com.example.tvshowsmallkotlin.listeners

import com.example.tvshowsmallkotlin.models.TvShow

interface TvShowListener {
    fun onClickTvShow(tvShow : TvShow);
}