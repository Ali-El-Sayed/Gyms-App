package com.example.gymcompse.ui.theme

import com.example.gymcompse.Gym
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GymsApiService {
    @GET("gyms.json")
    suspend fun getGymsList(): List<Gym>

    //get gym by id
    @GET("gyms.json?orderBy=\"id\"")
    suspend fun getGymById(
        @Query("equalTo") id: Int
    ): Map<String, Gym>

    // base/gyms.json?orderBy="id"&equalTo="7"&print=pretty
}