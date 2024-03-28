package com.example.tvshowsmallkotlin.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.repositories.TvShowDetailsRepository
import com.example.tvshowsmallkotlin.viewmodels.TvShowDetailsFactory
import com.example.tvshowsmallkotlin.viewmodels.TvShowDetailsViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowDetailsBinding
    private lateinit var tvShow: TvShow
    private lateinit var viewmodel: TvShowDetailsViewmodel
    private lateinit var viewmodelFactory: TvShowDetailsFactory
    private lateinit var repository: TvShowDetailsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)

        doInitialization()

    }

    private fun doInitialization() {

        // get data from main activity
         tvShow = (intent.getSerializableExtra("tv_show") as? TvShow)!!

        repository = TvShowDetailsRepository()
        viewmodelFactory = TvShowDetailsFactory(repository)
        viewmodel = ViewModelProvider(this, viewmodelFactory).get(TvShowDetailsViewmodel::class.java)

        getTvShowDetails(tvShow.id)

    }

    private fun getTvShowDetails(id: Int?) {
        val tvShowId = id.toString()

        CoroutineScope(Dispatchers.Main).launch {
            val tvShowDetailsResponse = viewmodel.getTvShowDetails(tvShowId)
            Log.e("tvShowDetailsResponse", "getTvShowDetails:  "+ tvShowDetailsResponse.value.toString() )

        }
    }
}