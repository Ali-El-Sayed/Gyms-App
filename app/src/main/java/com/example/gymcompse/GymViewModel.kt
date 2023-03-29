package com.example.gymcompse


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymcompse.ui.theme.GymsApiService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TAG
private const val TAG = "GymViewModel"

class GymViewModel : ViewModel() {
    private var _state = MutableLiveData(mutableListOf<Gym>())
    val state: LiveData<MutableList<Gym>>
        get() = _state

    private lateinit var gymsCall: Call<List<Gym>>
    private var apiService: GymsApiService

    // request gyms list
    private val job = Job()
    val scope = CoroutineScope(context = job + Dispatchers.IO)

    init {
        val retrofit: Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                "https://gym-compse-default-rtdb.firebaseio.com/"
            ).build()
        apiService = retrofit.create(GymsApiService::class.java)
        getGyms()
    }

    private fun getGyms() {
        scope.launch {
            val gyms = apiService.getGymsList() as MutableList<Gym>
            withContext(Dispatchers.Main) {
                _state.value = gyms
            }
        }
    }

    fun toggleFavorite(gymId: Int) {
        _state.value?.let { gyms ->
            val updatedGyms = gyms.map { gym ->
                if (gym.id == gymId) gym.copy(isFavorite = !gym.isFavorite)
                else gym
            }
            _state.value = updatedGyms.toMutableList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
