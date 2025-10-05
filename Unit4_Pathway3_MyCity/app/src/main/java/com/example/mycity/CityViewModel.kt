package com.example.mycity

import androidx.lifecycle.ViewModel
import com.example.mycity.data.StatisticPlace

import com.example.mycity.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        UiState()
    )
    val uiState: StateFlow<UiState> = _uiState
    fun updateCurrentCategory(selectedCategory: String) {
        _uiState.update {
            it.copy(
                selectedCategory = selectedCategory,
                listPlace = StatisticPlace.getListPlace[selectedCategory]
            )
        }
    }

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(selectedPlace = selectedPlace)
        }
    }

}

data class UiState(
    val selectedCategory: String = StatisticPlace.categoryDefault,
    val selectedPlace: Place = Place(0, 0, 0),
    val listPlace: List<Place>? = StatisticPlace.getListPlace[selectedCategory],

    )
