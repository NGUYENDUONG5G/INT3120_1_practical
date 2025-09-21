package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    getComponent()
                }
            }
        }
    }
}

@Composable
fun getComponent() {

    var index by remember { mutableStateOf(0) }

    var (image, tittle, author) = when (index) {
        0 -> Triple(R.drawable.image, "Ironman", "May 20")
        1 -> Triple(R.drawable.myuet, "My school", "August 13")
        2 -> Triple(R.drawable.ic_launcher_foreground, "android", "March 25")

        else -> Triple(R.drawable.ic_launcher_background, "background", "October 5")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(196, 194, 194))
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(200.dp),

            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .padding(40.dp)
                    .background(Color(222, 222, 62))
                    .width(200.dp),
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
                Color(201, 189, 199)
            )
        ) {
            Text(
                text = tittle,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            Text(
                text = author,

                )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = {
                if (index > 0) index--
            }, modifier = Modifier.width(150.dp)) {
                Text(text = "Previous")


            }
            Spacer(modifier = Modifier.width(50.dp))
            Button(onClick = {
                if (index < 3) index++
            }, modifier = Modifier.width(150.dp)) {
                Text(text = "Next")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun getComponentPreview() {
    ArtSpaceTheme {
        getComponent()
    }
}