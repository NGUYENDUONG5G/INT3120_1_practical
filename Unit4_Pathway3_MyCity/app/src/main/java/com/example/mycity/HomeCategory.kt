package com.example.mycity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun CategoryScreen(
    listCategory: List<String>,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn {
        items(listCategory) { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCategoryClick(category) }
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategory() {
    MyCityTheme {
        CategoryScreen(
            listCategory = StatisticPlace.getListCategory,
            onCategoryClick = { it -> print(it) })
    }
}