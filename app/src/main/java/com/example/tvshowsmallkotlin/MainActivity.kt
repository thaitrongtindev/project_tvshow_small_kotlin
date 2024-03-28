package com.example.tvshowsmallkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsmallkotlin.adapter.TvShowsAdapter
import com.example.tvshowsmallkotlin.databinding.ActivityMainBinding
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.viewmodels.MostPoplularTVShowsViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var viewmodel: MostPoplularTVShowsViewmodel
    private lateinit var adapterTvShow: TvShowsAdapter
    private var tvShows = ArrayList<TvShow>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        doInitialization()
        Log.e("TAG", "onCreate: ", )

    }

    private fun doInitialization() {
        viewmodel = ViewModelProvider(this).get(MostPoplularTVShowsViewmodel::class.java)

        adapterTvShow = TvShowsAdapter(tvShows)
        binding.tvShowRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTvShow
        }
        getMostPopularTVShows()    }

    private  fun getMostPopularTVShows() {
        Toast.makeText(this, "get", Toast.LENGTH_SHORT).show()
        Log.e("TAG", "getMostPopularTVShows: ", )
        viewmodel.getMostPopularTVShows(0).observe(this, Observer { mostPopularTvShows ->
            mostPopularTvShows?.let {
                tvShows.addAll(it.tvShows!!)
                adapterTvShow.notifyDataSetChanged()
            }
        })
    }

}