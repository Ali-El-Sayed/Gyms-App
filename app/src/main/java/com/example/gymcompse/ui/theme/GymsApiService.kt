package com.example.gymcompse.ui.theme

import com.example.gymcompse.Gym
import retrofit2.Call
import retrofit2.http.GET

interface GymsApiService {
    @GET("gyms.json")
    suspend fun getGymsList(): List<Gym>
}