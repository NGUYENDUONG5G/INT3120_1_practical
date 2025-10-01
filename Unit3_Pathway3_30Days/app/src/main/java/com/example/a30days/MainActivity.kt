package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30days.model.Daily
import com.example.a30days.model.DailyRepository.dailies
import com.example.a30days.ui.theme._30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysTheme {
                DaysApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysApp() {
    Scaffold(topBar = { topAppBar() }) { it ->
        LazyColumn(contentPadding = it) {
            items(dailies) {
                DaysScreenCard(
                    daily = it,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun DaysScreenCard(daily: Daily, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.clickable {
            expanded = !expanded
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Column(   modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )) {
            Text(
                text = stringResource(daily.index),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(daily.tittle),
                style = MaterialTheme.typography.displaySmall

            )


            Image(
                painter = painterResource(daily.image),
                contentDescription = stringResource(daily.tittle),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(64.dp)
            )
            if (expanded) {
                contentDay(daily.content,
                    modifier= Modifier.padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ))
            }

        }
    }
}

@Composable
fun contentDay(@StringRes content: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(content),
        style = MaterialTheme.typography.bodyLarge

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "30 Days",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun get2() {
    _30DaysTheme(darkTheme = true) {
        DaysApp()
    }
}

@Preview(showBackground = true)
@Composable
fun get1() {
    _30DaysTheme(darkTheme = false) {
        DaysApp()
    }
}
