package com.example.gymcompose

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gyms_table")
data class Gym(
    @PrimaryKey @ColumnInfo(name = "gym_id") val id: Int = 0,
    @SerializedName("gym_name") @ColumnInfo(name = "gym_name") val name: String = "",
    @SerializedName("gym_location") @ColumnInfo(name = "gym_location") val location: String = "",
    @SerializedName("is_open") var isOpen: Boolean = false,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean = false
)
