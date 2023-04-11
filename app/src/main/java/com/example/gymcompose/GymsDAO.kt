package com.example.gymcompose

import androidx.room.*

@Dao
interface GymsDAO {
    @Query("SELECT * FROM gyms_table")
    suspend fun getAll(): List<Gym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gyms: List<Gym>)

    @Update(entity = Gym::class)
    suspend fun update(gymFavouriteState: GymFavouriteState)
}