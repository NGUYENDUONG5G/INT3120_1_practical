package com.example.task2unit1.ui.theme

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity3 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Unit1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    getComponent3()
                }
            }
        }
    }
}

@Composable
fun getText(tittle: String, body: String, color: Color,modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().background(color).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = tittle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = body,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun getComponent3() {

    Row() {
        Column(modifier = Modifier.weight(1f)) {
            getText(
                "Text composable",
                "Displays text and follows the recommended Material Design guidelines.",
                Color(0xFFEADDFF), Modifier.weight(1f)
            )
            getText(
                "Row composable",
                "A layout composable that places its children in a horizontal sequence.",
                Color(0xFFB69DF8), Modifier.weight(1f)
            )

        }

        Column( modifier = Modifier.weight(1f)) {
            getText(
                "Image composable",
                "Creates a composable that lays out and draws a given Painter class object.",
                Color(0xFFD0BCFF), Modifier.weight(1f)
            )
            getText(
                "Column composable",
                "A layout composable that places its children in a vertical sequence.",
                Color(0xFFF6EDFF), Modifier.weight(1f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview3() {
    Task2Unit1Theme {
        getComponent3()
    }
}