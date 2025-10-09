package com.example.amphibians.data

import com.example.amphibians.model.Amphibians
import com.example.amphibians.network.RetrofitService

interface AppRepository {
    suspend fun listAmphibians(): List<Amphibians>
}

class NetworkRepository(private val retrofitService: RetrofitService) : AppRepository {
    override suspend fun listAmphibians(): List<Amphibians> = retrofitService.getAmphibians()

}