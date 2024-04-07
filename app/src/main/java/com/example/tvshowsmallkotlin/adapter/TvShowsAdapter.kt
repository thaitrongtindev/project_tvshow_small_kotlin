package com.example.tvshowsmallkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.databinding.ItemContainerTvShowBinding
import com.example.tvshowsmallkotlin.listeners.TvShowListener
import com.example.tvshowsmallkotlin.models.TvShow

class TvShowsAdapter(private var tvShows: List<TvShow>, val tvShowListener : TvShowListener) :
    RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContainerTvShowBinding.inflate(layoutInflater, parent, false)
        return TvShowsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        holder.bind(tvShows.get(position))
    }

    inner class TvShowsViewHolder(val binding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.tvShow = tvShow
            binding.executePendingBindings()

           binding.root.setOnClickListener {
               tvShowListener.onClickTvShow(tvShow)

           }

        }
    }
}