package com.example.flightsearch.Query

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {
    @Query("SELECT * FROM airport WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%'")
    fun searchAirports(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code != :departureCode")
    fun getPossibleDestinations(departureCode: String): Flow<List<Airport>>

    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE departure_code = :dep AND destination_code = :dest")
    suspend fun deleteFavorite(dep: String, dest: String)
}