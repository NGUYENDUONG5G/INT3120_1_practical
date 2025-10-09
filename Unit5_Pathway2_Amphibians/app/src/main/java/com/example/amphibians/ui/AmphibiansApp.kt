package com.example.amphibians.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(topBar = { TopAppBar(scroll) }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val uiModelView: UiViewModel = viewModel(factory = UiViewModel.factory)
            AmphibiansScreen(
                contentPaddingValues = it,
                uiState = uiModelView.uiState,
                action = { uiModelView.getPhotos() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(scroll: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        scrollBehavior = scroll,
        title = {
            Text(
                text = "Amphibians"
            )
        }
    )


}