package com.example.gymcompse

import com.google.gson.annotations.SerializedName

data class Gym(
    val id: Int,
    @SerializedName("gym_name") val name: String,
    @SerializedName("gym_location") val location: String,
    var isFavorite: Boolean = false
)

//make list of gyms with diffrent names and locations
val gymsList = mutableListOf(
    Gym(1, "Inspire", "123 Main Street, Anytown, USA"),
    Gym(2, "Uptown Gym", "456 Maple Avenue, Somewhereville, Canada"),
    Gym(3, "Core Values", "789 Oak Street, Nowheretown, Australia"),
    Gym(4, "Top Gym", "Akshya Nagar 1st Block 1st Cross, Rammurthy nagar, Bangalore-560016"),
    Gym(5, "Iron Paradise", "10 Downing Street, London, UK"),
    Gym(6, "Sweat Factory", "999 Beach Road, Paradise Island, Bahamas"),
    Gym(7, "Powerhouse Gym", "1234 Fifth Avenue, New York, USA"),
    Gym(8, "Fitness 365", "34th Street, Hollywood, USA"),
    Gym(9, "Muscle Factory", "78 York Road, Sydney, Australia"),
    Gym(10, "Fit Nation", "1 Canada Place, Toronto, Canada"),
    Gym(11, "Gold's Gym", "1234 Orange Street, Los Angeles, USA"),
    Gym(12, "Pump It Up", "555 Elm Street, New Orleans, USA"),
    Gym(13, "Body Builders", "444 Main Road, Mumbai, India"),
    Gym(14, "Flex Fitness", "3333 Sunset Boulevard, Hollywood, USA"),
    Gym(15, "Fitness Freaks", "99 Main Street, Cape Town, South Africa"),
    Gym(16, "Shape Up", "1 Bond Street, London, UK"),
    Gym(17, "Muscle Madness", "777 Park Avenue, Las Vegas, USA"),
    Gym(18, "Gym Rat", "666 Main Street, Sydney, Australia"),
    Gym(19, "Total Fitness", "1 Broadway, New York, USA"),
    Gym(20, "Fit Factory", "123 South Street, Perth, Australia")
)
