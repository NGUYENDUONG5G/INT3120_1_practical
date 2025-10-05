package com.example.mycity


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.StatisticPlace
import com.example.mycity.model.Place
import com.example.mycity.ui.theme.MyCityTheme


@Composable
fun PlaceDetailScreen(place: Place, contentPaddingValues: PaddingValues) {

    Column(
        modifier = Modifier
            .padding(contentPaddingValues)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.labelLarge
            )
            Image(
                painter = painterResource(place.image),
                contentDescription = stringResource(place.name),
                Modifier
                    .size(300.dp)
                    .padding(20.dp)
            )
            Text(
                text = stringResource(id = place.describe),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MyCityTheme {
        PlaceDetailScreen(StatisticPlace.defaultPlace, contentPaddingValues = PaddingValues())
    }
}