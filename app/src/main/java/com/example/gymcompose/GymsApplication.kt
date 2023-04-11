package com.example.gymcompose

import android.app.Application
import android.content.Context

// Expose Application Context to the whole app
class GymsApplication : Application() {
    init {
        application = this
    }

    companion object {
        lateinit var application: GymsApplication
        fun getApplicationContext(): Context = application.applicationContext
    }
}