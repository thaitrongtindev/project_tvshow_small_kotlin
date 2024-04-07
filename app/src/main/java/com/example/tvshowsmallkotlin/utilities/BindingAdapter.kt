package com.example.tvshowsmallkotlin.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("android:imageURL")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        try {
            // Load hình ảnh từ URL sử dụng Glide
            Glide.with(imageView.context).load(url).into(imageView)
        } catch (e: Exception) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace()
        }
    }
}
