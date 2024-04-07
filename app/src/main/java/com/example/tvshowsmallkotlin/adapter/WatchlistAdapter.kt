package com.example.tvshowsmallkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.databinding.ItemContainerTvShowBinding
import com.example.tvshowsmallkotlin.listeners.WatchlistListener
import com.example.tvshowsmallkotlin.models.TvShow
import kotlinx.coroutines.CoroutineScope

class WatchlistAdapter(val tvShows: List<TvShow>, val watchlistListener: WatchlistListener) : RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder>() {
    inner class WatchlistViewHolder(val binding: ItemContainerTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.tvShow = tvShow
            binding.executePendingBindings()
            binding.imageDelete.visibility = View.VISIBLE
            // click item on rcv
            binding.root.setOnClickListener {
                watchlistListener.onTvShowClicked(tvShow)
            }

            //click delete item
            binding.imageDelete.setOnClickListener {
                watchlistListener.onDeleteTvShow(tvShow, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val binding = ItemContainerTvShowBinding.inflate(LayoutInflater.from(
            parent.context
        ), parent, false)
        return WatchlistViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return tvShows.size
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bind(tvShows.get(position))
    }
}