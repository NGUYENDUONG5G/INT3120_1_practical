package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.network.RetrofitService

interface AppRepository {
    suspend fun searchBooks(query: String): List<BookItem>
    suspend fun getBookDetails(id: String): BookDetail
}
class NetworkAppRepository(private val service: RetrofitService): AppRepository{
    override suspend fun searchBooks(query: String): List<BookItem> {
        val response = service.searchBooks(query)
        Log.d("BookShelf", "🔗 Fetching from: https://www.googleapis.com/books/v1/volumes?q=$query")
        return response.items ?: emptyList()
    }
    override suspend fun getBookDetails(id: String): BookDetail {
        return service.getBookDetail(id)
    }

}