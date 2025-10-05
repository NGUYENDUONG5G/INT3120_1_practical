package com.example.mycity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.StatisticPlace
import com.example.mycity.model.Place
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun PlaceListScreen(
    listPlace: List<Place>?,
    onPlaceClick: (Place) -> Unit,
) {


    LazyColumn {
        items(listPlace ?: emptyList()) { place ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onPlaceClick(place) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(place.name),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(place.describe),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlace() {
    MyCityTheme {
        PlaceListScreen(
            listPlace = StatisticPlace.getListPlace[StatisticPlace.categoryDefault],
            onPlaceClick = {})
    }
}

