package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flightsearch.Query.EntityDao

@Database(entities = [Airport::class, Favorite::class], version = 1)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun entityDao(): EntityDao

    companion object {
        private var Instance: FlightDatabase? = null
        fun getDatabase(context: Context): FlightDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlightDatabase::class.java, "flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}