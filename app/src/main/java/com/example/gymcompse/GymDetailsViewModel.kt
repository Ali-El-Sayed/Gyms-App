package com.example.gymcompse

import androidx.lifecycle.*
import com.example.gymcompse.ui.theme.GymsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private lateinit var apiService: GymsApiService
    private var _state = MutableLiveData<Gym>()
    val state: LiveData<Gym>
        get() = _state


    init {
        val retrofit: Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                "https://gym-compse-default-rtdb.firebaseio.com/"
            ).build()
        apiService = retrofit.create(GymsApiService::class.java)

        val gymId = savedStateHandle.get<Int>("gym_id") ?: 0
        getGym(gymId)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = getGymFromRemoteServer(id)
            _state.value = gym ?: Gym()
        }
    }

    private suspend fun getGymFromRemoteServer(id: Int) = withContext(Dispatchers.IO) {
        apiService.getGymById(id)[id.toString()]
    }
}