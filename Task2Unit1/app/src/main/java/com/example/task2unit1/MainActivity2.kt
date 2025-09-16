package com.example.task2unit1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task2unit1.ui.theme.Task2Unit1Theme
import org.intellij.lang.annotations.JdkConstants

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Unit1Theme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    getComponent2()

                }
            }
        }
    }
}

@Composable
fun getComponent2(){
    val image = painterResource(R.drawable.ic_task_completed)
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = "All tasks completed",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top=24.dp,
                bottom = 8.dp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text ="Nice work!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    Task2Unit1Theme {
        getComponent2()
    }
}
