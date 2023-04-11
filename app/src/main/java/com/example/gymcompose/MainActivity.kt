package com.example.gymcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.net.Uri


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GymsAroundApp() }

    }


}

@Composable
private fun GymsAroundApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gyms") {
        composable(route = "gyms") {
            GymsScreen() { gymId -> navController.navigate(route = "gyms/$gymId") }
        }
        composable(
            route = "gyms/{gym_id}",
            arguments = listOf(navArgument(name = "gym_id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://gymsaround.example.com/details/{gym_id}"
                action = Intent.ACTION_VIEW
            })
        ) {
            GymDetailsScreen()
        }
    }
}
/**
 * https://developer.android.com/training/app-links/deep-linking.html
 * Note: Starting in Android 12 (API level 31), a generic web intent resolves to an activity in your app only.
 * if your app is approved for the specific domain contained in that web intent.
 * If your app isn't approved for the domain, the web intent resolves to the user's default browser app instead.
 * */