package com.example.bookshelf.network

import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
    ): BooksResponse

    @GET("volumes/{id}")
    suspend fun getBookDetail(@Path("id") volumeId: String): BookDetail
}