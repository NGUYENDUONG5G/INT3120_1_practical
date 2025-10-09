package com.example.amphibians.ui

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AppRepository
import com.example.amphibians.model.Amphibians
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface UiState {
    object Loading : UiState
    data class Success(val listPhotos: List<Amphibians>) : UiState
    object Error : UiState
}


class UiViewModel(val appRepository: AppRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getPhotos()
    }

    fun getPhotos() {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                UiState.Success(appRepository.listAmphibians())
            } catch (e: Exception) {
                e.printStackTrace()
                UiState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val repository = application.container.appRepository
                UiViewModel(repository)
            }
        }
    }
}

