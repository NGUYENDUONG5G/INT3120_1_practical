package com.example.bookshelf.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.AppRepository
import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookItem
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadLocalRandom.current

sealed interface UiState {
    data class Error(val lastUi: BookShelfUi? = null) : UiState
    data object Loading : UiState
    data class BookShelfUi(
        val query: String = "",
        val books: List<BookItem> = emptyList(),
        val selectedBook: BookDetail? = null
    ) : UiState
}
class AppViewModel(val repository: AppRepository): ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set


    var query by mutableStateOf("")
        private set

    fun updateQuery(newQuery: String) {
        query = newQuery
    }

    fun loadBooks(query: String = this.query) {
        if (query.isBlank()) return
        viewModelScope.launch {
            Log.d("BookShelf", "🔍 Searching query: '$query'")
            val lastUi = uiState as? UiState.BookShelfUi
            uiState = UiState.Loading
            try {
                val books = repository.searchBooks(query)
                uiState = UiState.BookShelfUi(
                    query = query,
                    books = books,
                    selectedBook = null
                )
            } catch (e: Exception) {
                Log.e("BookShelf", "❌ Error loading books: ${e.message}", e)
                uiState = UiState.Error(lastUi)
            }
        }
    }

    fun loadBookDetail(id: String) {
        viewModelScope.launch {
            val lastUi = uiState as? UiState.BookShelfUi
            try {
                val book = repository.getBookDetails(id)
                uiState = UiState.BookShelfUi(
                    query = lastUi?.query ?: "",
                    books = lastUi?.books ?: emptyList(),
                    selectedBook = book
                )
            } catch (e: Exception) {

                uiState = UiState.Error(lastUi)
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val repository = application.container.appRepository
                AppViewModel(repository)
            }
        }
    }
}
