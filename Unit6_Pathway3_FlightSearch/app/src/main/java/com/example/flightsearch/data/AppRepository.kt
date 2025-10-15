package com.example.flightsearch.data

import androidx.room.Insert
import androidx.room.Query
import com.example.flightsearch.Query.EntityDao
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun searchAirports(query: String): Flow<List<Airport>>
    fun getPossibleDestinations(departureCode: String): Flow<List<Airport>>
    fun getFavorites(): Flow<List<Favorite>>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavorite(dep: String, dest: String)

}

class FlightRepository(private val entityDao: EntityDao) : AppRepository {
    override fun getPossibleDestinations(departureCode: String): Flow<List<Airport>> =
        entityDao.getPossibleDestinations(departureCode)

    override fun searchAirports(query: String): Flow<List<Airport>> =
        entityDao.searchAirports(query)

    override fun getFavorites(): Flow<List<Favorite>> = entityDao.getFavorites()

    override suspend fun deleteFavorite(dep: String, dest: String) =
        entityDao.deleteFavorite(dep, dest)

    override suspend fun insertFavorite(favorite: Favorite) = entityDao.insertFavorite(favorite)
}