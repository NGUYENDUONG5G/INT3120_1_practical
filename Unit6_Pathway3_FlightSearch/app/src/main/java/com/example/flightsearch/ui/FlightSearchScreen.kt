package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(viewModel: FlightViewModel = viewModel(factory = FlightViewModel.factory)) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedDeparture by remember { mutableStateOf<Airport?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flight Search") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(
                query = uiState.query,
                onQueryChange = {
                    selectedDeparture = null
                    viewModel.saveSearchQuery(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            when {
                uiState.query.isBlank() -> {
                    FavoriteList(uiState.favorites)
                }

                selectedDeparture != null -> {
                    FlightList(
                        departureAirport = selectedDeparture!!,
                        destinations = uiState.results.filter { it != selectedDeparture },
                        favorites = uiState.favorites,
                        onToggleFavorite = { dep, dest ->
                            val isFav = uiState.favorites.any {
                                it.departure_code == dep && it.destination_code == dest
                            }
                            if (isFav) viewModel.removeFavorite(dep, dest)
                            else viewModel.addFavorite(dep, dest)
                        }
                    )
                }

                uiState.results.isNotEmpty() -> {
                    AirportList(
                        airports = uiState.results,
                        onAirportSelected = { selected ->
                            selectedDeparture = selected
                        }
                    )
                }

                else -> {
                    Text(
                        "No results found.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Enter departure airport") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { })
    )
}

@Composable
fun AirportList(
    airports: List<Airport>,
    onAirportSelected: (Airport) -> Unit
) {
    LazyColumn {
        items(airports) { airport ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onAirportSelected(airport) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = airport.iata_code,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = airport.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun FlightList(
    departureAirport: Airport,
    destinations: List<Airport>,
    favorites: List<Favorite>,
    onToggleFavorite: (String, String) -> Unit
) {
    Text(
        text = "Flights from ${departureAirport.iata_code}",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyColumn {
        items(destinations) { dest ->
            val isFavorite = favorites.any {
                it.departure_code == departureAirport.iata_code &&
                        it.destination_code == dest.iata_code
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("DEPART: ${departureAirport.iata_code} - ${departureAirport.name}")
                        Text("ARRIVE: ${dest.iata_code} - ${dest.name}")
                    }

                    IconButton(onClick = {
                        onToggleFavorite(
                            departureAirport.iata_code,
                            dest.iata_code
                        )
                    }) {
                        Icon(
                            painter = if (isFavorite) painterResource(R.drawable.star) else painterResource(R.drawable.star_outline),
                            contentDescription = null,
                            tint = if (isFavorite)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteList(favorites: List<Favorite>) {
    Text(
        text = "Favorite routes",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyColumn {
        items(favorites) { fav ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("DEPART: ${fav.departure_code}")
                    Text("ARRIVE: ${fav.destination_code}")
                }
            }
        }
    }
}
