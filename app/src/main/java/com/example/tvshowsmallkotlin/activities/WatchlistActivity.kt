package com.example.tvshowsmallkotlin.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.adapter.WatchlistAdapter
import com.example.tvshowsmallkotlin.databinding.ActivityWatchlistBinding
import com.example.tvshowsmallkotlin.listeners.WatchlistListener
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.viewmodels.WatchlistViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchlistActivity : AppCompatActivity(), WatchlistListener {
    private lateinit var binding : ActivityWatchlistBinding
    private lateinit var viewmodel: WatchlistViewmodel
    private lateinit var watchlist: MutableList<TvShow>
    private lateinit var watchlist1: MutableLiveData<TvShow>
    private lateinit var watchlistAdapter: WatchlistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_watchlist)
        initialization()
        }

    private fun initialization() {
        viewmodel = ViewModelProvider(this).get(WatchlistViewmodel::class.java)
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }

        watchlist = mutableListOf();
      //  loadWatchlist()

        binding.imageViewSearch.setOnClickListener {
            onClickSearchTvShow()
        }
    }

    private fun onClickSearchTvShow() {
        binding.editTextSearch.visibility = View.VISIBLE
        binding.tvWatchlist.visibility = View.GONE


        val strQuery = binding.editTextSearch.text.toString().toString()
        searchTvShowFromWatchlist(strQuery)
    }

    private fun searchTvShowFromWatchlist(strQuery: String) {
        viewmodel.searchTvShowByName(strQuery).observe(
            this, Observer {
                Log.e("TAG", "searchTvShowFromWatchlist: " +it.size )
            }
        )
    }


    private fun loadWatchlist() {
    CoroutineScope(Dispatchers.IO).launch {
        watchlist = viewmodel.loadWatchlist()

        Log.e("watchlist", watchlist.toString() )
        withContext(Dispatchers.Main) {
            watchlistAdapter = WatchlistAdapter(watchlist, this@WatchlistActivity)
            binding.watchlistRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = watchlistAdapter
            }


            watchlistAdapter.notifyDataSetChanged()
        }
    }
}

    override fun onStart() {
        super.onStart()
        loadWatchlist()
    }



    override fun onTvShowClicked(tvShow: TvShow) {
       val intent = Intent(this, TvShowDetailsActivity::class.java)
        intent.putExtra("tv_show", tvShow)
        startActivity(intent)
    }

    override fun onDeleteTvShow(tvShow: TvShow, position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            viewmodel.removeFromWatchlist(tvShow)
            withContext(Dispatchers.Main) {
                watchlist.removeAt(position)
                watchlistAdapter.notifyItemRangeChanged(position, watchlistAdapter.itemCount)
                watchlistAdapter.notifyItemRemoved(position)
            }


        }
    }



}