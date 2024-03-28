package com.example.tvshowsmallkotlin.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.databinding.ActivityMainBinding
import com.example.tvshowsmallkotlin.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowsmallkotlin.models.TvShow

class TvShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowDetailsBinding
    private lateinit var tvShow: TvShow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)

        doInitialization()

    }

    private fun doInitialization() {

        // get data from main activity
         tvShow = (intent.getSerializableExtra("tv_show") as? TvShow)!!
    }
}