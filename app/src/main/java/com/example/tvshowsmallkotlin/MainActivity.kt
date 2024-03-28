package com.example.tvshowsmallkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowsmallkotlin.viewmodels.MostPoplularTVShowsViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MostPoplularTVShowsViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        viewmodel = ViewModelProvider(this).get(MostPoplularTVShowsViewmodel::class.java)

        getMostPopularTVShows()
    }

    private  fun getMostPopularTVShows() {
        Toast.makeText(this, "get", Toast.LENGTH_SHORT).show()
        viewmodel.getMostPopularTVShows(0).observe(this, Observer {
            if (it != null) {
                Log.e("IT", "getMostPopularTVShows: " + it.toString() )
            }
        })
    }
}