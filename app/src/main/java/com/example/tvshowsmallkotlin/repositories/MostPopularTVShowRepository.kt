import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsmallkotlin.network.ApiService
import com.example.tvshowsmallkotlin.responses.TVShowResponse
import com.example.tvshowsmallkotlin.responses.TvShowDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.Call

import retrofit2.Response

class MostPopularTVShowRepository {
    private val apiService : ApiService = ApiClient().getRetrofitInstance().create(ApiService::class.java)

    suspend fun getMostPopularTVShows(page: Int): LiveData<TVShowResponse> {
        return withContext(Dispatchers.IO) {
            val data = MutableLiveData<TVShowResponse>()
            try {
                val response = apiService.getMostPopularTVShows(page)
                if (response.isSuccessful) {
                    data.postValue(response.body())
                } else {
                    // Handle unsuccessful response
                    Log.e("TvShowDetailsRepository", "getTvShowDetails: Unsuccessful response")
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("TvShowDetailsRepository", "getTvShowDetails: Error - ${e.message}")
            }
            data
        }
    }
}
