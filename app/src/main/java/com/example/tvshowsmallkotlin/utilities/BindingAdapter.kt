package com.example.tvshowsmallkotlin.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("android:imageURL")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        try {
            imageView.alpha = 0f
            Glide.with(imageView.context).load(url).into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
