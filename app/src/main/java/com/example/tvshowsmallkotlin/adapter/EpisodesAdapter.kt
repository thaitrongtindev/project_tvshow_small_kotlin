package com.example.tvshowsmallkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.databinding.ItemContainerEpisodeBinding
import com.example.tvshowsmallkotlin.models.Episode

class EpisodesAdapter(val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {
    class EpisodesViewHolder(val binding: ItemContainerEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindEpisodes(episode: Episode) {
            var title = "S"
            var season = episode.season
            if (season?.length == 1) {
                season = "0$season"
            }
            var episodeNumber = episode.episode
            if (episodeNumber?.length == 1) {
                episodeNumber = "0$episodeNumber"
            }
            episodeNumber = "E$episodeNumber"
            title += season + episodeNumber
            binding.title = title
            binding.name = episode.name
            binding.airDate = episode.airDate
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodesAdapter.EpisodesViewHolder {
        val binding = ItemContainerEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesAdapter.EpisodesViewHolder, position: Int) {
        holder.bindEpisodes(episodes.get(position))
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}