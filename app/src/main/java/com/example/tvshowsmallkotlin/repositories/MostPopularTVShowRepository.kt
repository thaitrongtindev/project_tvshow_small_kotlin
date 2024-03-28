import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsmallkotlin.network.ApiService
import com.example.tvshowsmallkotlin.responses.TVShowResponse
import retrofit2.Callback
import retrofit2.Call

import retrofit2.Response

class MostPopularTVShowRepository {
    private val apiService : ApiService = ApiClient().getRetrofitInstance().create(ApiService::class.java)

    fun getMostPopularTVShows(page: Int): LiveData<TVShowResponse> {
        val data = MutableLiveData<TVShowResponse>()
        try {
            apiService.getMostPopularTVShows(page).enqueue(object : Callback<TVShowResponse> {
                override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    } else {
                        // Handle unsuccessful response
                        Log.e("TAG", "getMostPopularTVShows: Unsuccessful response")
                    }
                }

                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    // Handle failure
                    Log.e("TAG", "getMostPopularTVShows: Failure - ${t.message}")
                }
            })
        } catch (e: Exception) {
            // Handle error
            Log.e("TAG", "getMostPopularTVShows: Error - ${e.message}")
        }
        return data
    }
}
