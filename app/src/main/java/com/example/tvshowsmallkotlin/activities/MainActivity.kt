package com.example.tvshowsmallkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.adapter.TvShowsAdapter
import com.example.tvshowsmallkotlin.databinding.ActivityMainBinding
import com.example.tvshowsmallkotlin.listeners.TvShowListener
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.viewmodels.MostPoplularTVShowsViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), TvShowListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewmodel: MostPoplularTVShowsViewmodel
    private lateinit var adapterTvShow: TvShowsAdapter
    private var tvShows = ArrayList<TvShow>()
    private var currentPage = 1
    private var totalAvailablePage = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        doInitialization()
        Log.e("TAG", "onCreate: ")

    }

    private fun doInitialization() {
        viewmodel = ViewModelProvider(this).get(MostPoplularTVShowsViewmodel::class.java)

        adapterTvShow = TvShowsAdapter(tvShows, this)
        binding.tvShowRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTvShow
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    // check  scrolled to the last
                    if (!recyclerView.canScrollVertically(1)) {
                        // hanlde event
                        if (currentPage <= totalAvailablePage) {
                            currentPage += 1;
                            getMostPopularTVShows()
                        }
                    }
                }
            })
        }
        getMostPopularTVShows()

        binding.imageViewWatchlist.setOnClickListener {
            startActivity(Intent(this, WatchlistActivity::class.java))
        }

        // click search tvshow
        binding.imageViewSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }



    private fun getMostPopularTVShows() {
        toggleLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val mostPopularTvShows = viewmodel.getMostPopularTVShows(currentPage)
            withContext(Dispatchers.Main) {
                mostPopularTvShows.observe(this@MainActivity, Observer { result ->
                    result?.let {
                        toggleLoading()
                        totalAvailablePage = it.tvShows!!.size
                        tvShows.addAll(it.tvShows!!)
                        adapterTvShow.notifyDataSetChanged()
                    }
                })
            }
        }
    }





    private fun toggleLoading() {
        // current page = 1
        if (currentPage == 1) {
            if (binding.isLoading != null && binding.isLoading == true) {
                binding.isLoading = false
            } else {
                // (binding.isLoading == null && binding.isLoading == false)
                binding.isLoading = true
            }
        } else {
            // current page != 1
            if (binding.isLoadingMore != null && binding.isLoadingMore == true) {
                binding.isLoadingMore = false
            } else {
                binding.isLoadingMore = true
            }
        }
    }

    override fun onClickTvShow(tvShow: TvShow) {
        val intent = Intent(this, TvShowDetailsActivity::class.java)
        intent.putExtra("tv_show", tvShow)
        startActivity(intent)
    }

}