package com.example.flightsearch

import android.app.Application
import com.example.flightsearch.data.AppContainer
import com.example.flightsearch.data.FlightContainer

class FlightApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = FlightContainer(this)
    }
}