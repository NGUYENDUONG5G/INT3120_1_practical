package com.example.task2unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task2unit1.ui.theme.Task2Unit1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Unit1Theme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    getComponent(title = "Jetpack Compose tutorial",
                        body = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                        "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
                        modifier = Modifier.fillMaxSize())
                }
                }
            }
        }
    }


@Composable
fun getComponent(title: String,body: String,end:String,modifier: Modifier) {
    Column {
        getImage(modifier)
        getTittle(title,modifier)
        getBody(body,modifier)
        getEnd(end,modifier)
    }
}

@Composable
fun getImage(modifier: Modifier){
    val image= painterResource(R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = null
    )
}

@Composable
fun getTittle(title: String,modifier: Modifier){
    Text(
        text = title,
        textAlign = TextAlign.Start,
        fontSize = 24.sp,
        modifier= Modifier.padding(16.dp)
    )
}

@Composable
fun getBody(body: String,modifier: Modifier){
    Text(
        text = body,
        textAlign = TextAlign.Justify,
        modifier= Modifier.padding(
            start = 16.dp, end = 16.dp
        )

    )
}

@Composable
fun getEnd(end: String, modifier: Modifier){
    Text(
        text = end,
        textAlign = TextAlign.Justify,
        modifier= Modifier.padding(16.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Task2Unit1Theme {
        getComponent(title = "Jetpack Compose tutorial",
            body = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.", modifier = Modifier)
    }
}