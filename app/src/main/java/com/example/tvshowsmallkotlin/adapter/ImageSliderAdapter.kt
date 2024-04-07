package com.example.tvshowsmallkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter(val sliderImage: Array<String>) : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {
    class ImageSliderViewHolder(val binding: ItemContainerSliderImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindImageSlider(s: String) {
                binding.imageURL = s
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val binding = ItemContainerSliderImageBinding.inflate(LayoutInflater.from(parent.context)
        ,parent, false)
        return  ImageSliderViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return sliderImage.size
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindImageSlider(sliderImage[position])
    }
}