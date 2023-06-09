package com.example.gymcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymcompose.ui.theme.GymsApiService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TAG
private const val TAG = "GymViewModel"

class GymViewModel : ViewModel() {
    private var _state = MutableLiveData(mutableListOf<Gym>())
    val state: LiveData<MutableList<Gym>>
        get() = _state

    private var gymsDao = GymsDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    private lateinit var gymsCall: Call<List<Gym>>
    private var apiService: GymsApiService

    // request gyms list
    private val job = Job()
    val scope = CoroutineScope(context = job + Dispatchers.IO)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        val retrofit: Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                "https://gym-compse-default-rtdb.firebaseio.com/"
            ).build()
        apiService = retrofit.create(GymsApiService::class.java)
        getGyms()
    }

    private fun getGyms() {
        viewModelScope.launch(exceptionHandler) {
            val gyms = getGymsFromRemoteServer()
            _state.value = gyms.toMutableList()
        }
    }

    // Retroift Interface call to get gyms list
    private suspend fun getGymsFromRemoteServer() = withContext(Dispatchers.IO) {
        //try catch block to handle exceptions
        try {
            val gymsList = apiService.getGymsList()
            gymsDao.insertAll(gymsList)
            return@withContext gymsList // return to the caller in the main thread
        } catch (e: Exception) {
            e.printStackTrace()
            gymsDao.getAll()
        }

    }

    fun toggleFavorite(gymId: Int) {
        _state.value?.let { gyms ->
            val updatedGyms = gyms.map { gym ->
                if (gym.id == gymId) gym.copy(isFavourite = !gym.isFavourite)
                else gym
            }
            _state.value = updatedGyms.toMutableList()
            viewModelScope.launch(exceptionHandler) {
                _state.value?.get(gymId)?.let { toggleFavouriteGym(gymId, !it.isFavourite) }
            }
        }
    }

    private suspend fun toggleFavouriteGym(gymId: Int, currentFavouriteState: Boolean) =
        withContext(Dispatchers.IO) {
            gymsDao.update(GymFavouriteState(gymId, !currentFavouriteState))
        }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}


