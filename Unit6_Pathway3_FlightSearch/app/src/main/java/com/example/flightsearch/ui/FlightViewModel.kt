package com.example.flightsearch.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.AppRepository
import com.example.flightsearch.data.Favorite
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(
    val query: String = "",
    val results: List<Airport> = emptyList(),
    val favorites: List<Favorite> = emptyList()
)

class FlightViewModel(
    private val repository: AppRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val SEARCH_QUERY = stringPreferencesKey("search_query")
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()
    private val favoritesFlow: Flow<List<Favorite>> = repository.getFavorites()
    init {
        viewModelScope.launch {
            val saved = dataStore.data.map { it[SEARCH_QUERY] ?: "" }.first()
            _query.value = saved
        }
    }
    val uiState: StateFlow<UiState> =
        _query.flatMapLatest { query ->
            if (query.isBlank()) {
                favoritesFlow.map { favorites ->
                    UiState(query = query, favorites = favorites)
                }
            } else {
                flow {
                    repository.searchAirports(query).collect { results ->
                        val favorites = favoritesFlow.first()
                        emit(UiState(query = query, results = results, favorites = favorites))
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState()
        )

    fun saveSearchQuery(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            dataStore.edit { prefs ->
                prefs[SEARCH_QUERY] = newQuery
            }
        }
    }

    fun addFavorite(dep: String, dest: String) {
        viewModelScope.launch {
            repository.insertFavorite(Favorite(departure_code = dep, destination_code = dest))
        }
    }

    fun removeFavorite(dep: String, dest: String) {
        viewModelScope.launch {
            repository.deleteFavorite(dep, dest)
        }
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as FlightApplication
                val container = app.container
                FlightViewModel(container.repository, container.dataStore)
            }
        }
    }
}
