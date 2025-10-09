package com.example.amphibians.data

import com.example.amphibians.network.RetrofitService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val appRepository: AppRepository
}
class NetworkAppContainer(): AppContainer{
    val baseUrl="https://android-kotlin-fun-mars-server.appspot.com/"
    val retrofit= Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val netWorkRetroService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
    override val appRepository: AppRepository by lazy {
        NetworkRepository(netWorkRetroService)
    }

}