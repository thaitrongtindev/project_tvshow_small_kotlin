package com.example.tvshowsmallkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsmallkotlin.adapter.TvShowsAdapter
import com.example.tvshowsmallkotlin.databinding.ActivitySearchBinding
import com.example.tvshowsmallkotlin.listeners.TvShowListener
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.viewmodels.SearchTvShowViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Timer
import java.util.TimerTask

class SearchActivity : AppCompatActivity() , TvShowListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewmodel: SearchTvShowViewmodel

    private var currentPage = 1
    private var totalAvailablePages = 1
    private lateinit var searchAdapter: TvShowsAdapter
    private lateinit var tvShows: MutableList<TvShow>
    private lateinit var timer: Timer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doInitialization()

    }

    private fun doInitialization() {

        viewmodel = ViewModelProvider(this).get(SearchTvShowViewmodel::class.java)

        tvShows = mutableListOf()
        searchAdapter = TvShowsAdapter(tvShows, this)
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Leave empty if no specific action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Leave empty if no specific action needed
            }

            override fun afterTextChanged(s: Editable) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            Handler(Looper.getMainLooper()).post {
                                currentPage = 1
                                totalAvailablePages = 1
                                searchTvShow(s.toString())
                            }
                        }
                    }, 800)
                } else {
                    tvShows.clear()
                    searchAdapter.notifyDataSetChanged()
                }
            }
        })
        binding.searchRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!binding.searchRecyclerView.canScrollVertically(1)) {
                        if (!binding.edtSearch.getText().toString().isEmpty()) {
                            if (currentPage < totalAvailablePages) {
                                currentPage += 1
                                searchTvShow(binding.edtSearch.getText().toString())
                            }
                        }
                    }
                }
            })
        binding.edtSearch.requestFocus()
    }


    private fun searchTvShow(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchTvShow = viewmodel.searchTvShow(query, currentPage)
            withContext(Dispatchers.Main) {
                searchTvShow.observe(this@SearchActivity, Observer {
                    Log.e("TAG", "searchTvShow: " + it.tvShows )
                    it.tvShows?.let { it1 ->
                        // Clear existing data if it's the first page
                        if (currentPage == 1) {
                            tvShows.clear()
                        }
                        tvShows.addAll(it1)
                    }
                    searchAdapter.notifyDataSetChanged()
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