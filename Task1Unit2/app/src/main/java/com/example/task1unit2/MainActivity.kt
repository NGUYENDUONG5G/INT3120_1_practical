package com.example.task1unit2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.task1unit2.ui.theme.Task1Unit2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember{ mutableStateOf(0) }
    var maxSqueezes by remember { mutableStateOf(0) }

    val (image, text) = when (step) {
        1 -> Pair(R.drawable.lemon_tree, "Tap the lemon tree to select a lemon")
        2 -> Pair(R.drawable.lemon_squeeze, "Keep tapping the lemon to squeeze it")
        3 -> Pair(R.drawable.lemon_drink, "Tap the lemonade to drink it")
        else -> Pair(R.drawable.lemon_restart, "Tap the empty glass to start again")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Lemonade",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFEB3B))
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                ),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = text,
                modifier = Modifier
                    .size(200.dp)
                    .clickable {
                        when (step) {
                            1 -> {
                                step = 2
                                maxSqueezes = Random.nextInt(2, 5)
                                squeezeCount = 0
                            }

                            2 -> {
                                squeezeCount++
                                if (squeezeCount >= maxSqueezes) step = 3
                            }

                            3 -> step = 4
                            4 -> step = 1
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = text,
                fontSize = 18.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Task1Unit2Theme {
        LemonadeApp()
    }
}
