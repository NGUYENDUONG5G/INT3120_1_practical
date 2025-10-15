package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val FLIGHT_PREFERENCES = "flight_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FLIGHT_PREFERENCES
)

interface AppContainer {
    val repository: AppRepository
    val dataStore: DataStore<Preferences>
}

class FlightContainer(context: Context) : AppContainer {
    override val repository: AppRepository by lazy {
        FlightRepository(FlightDatabase.getDatabase(context).entityDao())
    }
    override val dataStore: DataStore<Preferences> = context.dataStore

}
