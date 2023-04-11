package com.example.gymcompose

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "gyms_favourite_table", primaryKeys = ["gym_id"])
data class GymFavouriteState(
    @ColumnInfo(name = "gym_id") val gymId: Int,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean
)