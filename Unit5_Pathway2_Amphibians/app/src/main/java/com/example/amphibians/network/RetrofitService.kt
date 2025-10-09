package com.example.amphibians.network

import com.example.amphibians.model.Amphibians
import retrofit2.http.GET

interface RetrofitService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibians>
}