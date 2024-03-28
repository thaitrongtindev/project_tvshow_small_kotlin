package com.example.tvshowsmallkotlin.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.tvshowsmallkotlin.R
import com.example.tvshowsmallkotlin.adapter.ImageSliderAdapter
import com.example.tvshowsmallkotlin.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowsmallkotlin.models.TvShow
import com.example.tvshowsmallkotlin.repositories.TvShowDetailsRepository
import com.example.tvshowsmallkotlin.viewmodels.TvShowDetailsFactory
import com.example.tvshowsmallkotlin.viewmodels.TvShowDetailsViewmodel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class TvShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowDetailsBinding
    private lateinit var tvShow: TvShow
    private lateinit var viewmodel: TvShowDetailsViewmodel
    private lateinit var viewmodelFactory: TvShowDetailsFactory
    private lateinit var repository: TvShowDetailsRepository
    private val handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable? = null

    private lateinit var bottonSheetDialog: BottomSheetDialog


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
        binding.imageBack.setOnClickListener {
            onBackPressed()
            finish()
        }

    }

    private fun getTvShowDetails(id: Int?) {
        val tvShowId = id.toString()

        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.getTvShowDetails(tvShowId).observe(this@TvShowDetailsActivity, Observer { tvShowDetailsResponse ->
                tvShowDetailsResponse?.let {

                    // image slider
                    loadImageSlider(tvShowDetailsResponse.tvShowDetails.pictures)
                  // image url TVShow
                    binding.tvShowImageURL = tvShowDetailsResponse.tvShowDetails.imagePath
                    // description
                    binding.description = tvShowDetailsResponse.tvShowDetails.description
                    // set rating
                    binding.rating = String.format(Locale.getDefault(),
                            "%.2f",
                        java.lang.Double.parseDouble(
                            tvShowDetailsResponse.tvShowDetails.rating))

                    if (tvShowDetailsResponse.tvShowDetails.genres != null) {
                        binding.setGenre(
                            tvShowDetailsResponse.tvShowDetails.genres.get(0)
                        )
                    } else {
                        binding.setGenre("N/A")
                    }
                    binding.setRuntime(
                        tvShowDetailsResponse.tvShowDetails.runtime + "Min"
                    )

                    // click read more
                    binding.textReadMore.setOnClickListener {
                        if (binding.textReadMore.text.toString() == "Read More") {
                            binding.textDescription.maxLines = Int.MAX_VALUE // Hiển thị nhiều dòng văn bản
                            binding.textDescription.ellipsize = null
                            binding.textReadMore.text = getString(R.string.read_less) // Sửa lại thành `text` thay vì `setText`
                        } else {
                            binding.textDescription.maxLines = 4 // Hiển thị chỉ 4 dòng văn bản
                            binding.textDescription.ellipsize = TextUtils.TruncateAt.END
                            binding.textReadMore.text = getString(R.string.read_more) // Sửa lại thành `text` thay vì `setText`
                        }
                    }

                    // click btn website
                    binding.btnWebsite.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(tvShowDetailsResponse.tvShowDetails.url))
                        startActivity(intent)
                    }
                    //
                    loadBasicTvShowDetails()

                    // clic

                }
            })
        }
    }

    private fun loadBasicTvShowDetails() {
        Log.e("TAG", "loadBasicTvShowDetails: " + tvShow.name )
        binding.textName.text = tvShow.name
        binding.textCountry.text = tvShow.network + " ( " + tvShow.country + " ) "
        binding.textStatus.text = tvShow.status
        binding.textStartDate.text = tvShow.startDate
    }

    private fun loadImageSlider(sliderImages: Array<String>) {

        //view pager 2
        binding.sliderViewPager.setAdapter(ImageSliderAdapter(sliderImages))
        // circle indicator
        binding.circleIndicator.setViewPager(binding.sliderViewPager)

        //setting viewpager2
        binding.sliderViewPager.setOffscreenPageLimit(3)
        binding.sliderViewPager.setClipToPadding(false)
        binding.sliderViewPager.setClipChildren(false)

        // effect tranformer page
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.sliderViewPager.setPageTransformer(compositePageTransformer)



        // listener event viewpager2 transfer page
        binding.sliderViewPager.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (runnable != null) {
                    handler.removeCallbacks(runnable)
                }
                if (runnable != null) {
                    handler.postDelayed(runnable, 2000)
                }
            }
        })
    }

}