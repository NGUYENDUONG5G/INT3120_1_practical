package com.example.bookshelf.data

import com.example.bookshelf.network.RetrofitService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val appRepository: AppRepository
}

class NetworkAppContainer() : AppContainer {
    val baseUrl = "https://www.googleapis.com/books/v1/"
    val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(Json {
        ignoreUnknownKeys = true
        isLenient = true
    }.asConverterFactory("application/json".toMediaType())).build()

    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
    override val appRepository: AppRepository by lazy {
        NetworkAppRepository(retrofitService)
    }
}