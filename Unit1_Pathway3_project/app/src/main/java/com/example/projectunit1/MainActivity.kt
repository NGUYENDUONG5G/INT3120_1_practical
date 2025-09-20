package com.example.projectunit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectunit1.ui.theme.ProjectUnit1Theme
import kotlin.math.round


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectUnit1Theme {
                Surface(modifier = Modifier.background(Color(90, 245, 66))) {
                    getComponent()
                }
            }
        }
    }
}

@Composable
fun getComponent() {
    Column(modifier = Modifier.padding(30.dp)) {
        getTile("Nguyễn Nho Dương", "King of Rap")
        getInfo("0123456789", "kingofrap123@gmail.com")
    }
}

@Composable
fun getTile(name: String, tittle: String) {
    val image = painterResource(R.drawable.ic_launcher_foreground)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        Image(
            painter = image,
            contentDescription = null,

            )

        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = tittle,
            fontSize = 12.sp

        )
    }
}

@Composable
fun getInfo(numberPhone: String, email: String) {

    Column(horizontalAlignment = Alignment.Start) {
        Row {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = null,
                modifier = Modifier.scale(0.5F)
            )
            Text(
                text = numberPhone,
                fontSize = 10.sp
            )
        }
        Row {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = null,
                modifier = Modifier.scale(0.5F)
            )
            Text(
                text = email,
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun getComponentPreview() {
    ProjectUnit1Theme {
        getComponent()
    }
}