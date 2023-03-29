package com.example.gymcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymcompse.GymsScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            GymsScreen()
        }
    }
}

@Composable
fun Card() {
    var title by remember { mutableStateOf("Uptwon") }
    var location by remember { mutableStateOf("Akshya Nagar 1st Block 1st Cross, Rammurthy nagar, Bangalore-560016") }
    Row(
        modifier = Modifier
            .padding(10.dp, 10.dp, 10.dp, 10.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Location",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
            MyText(name = title, location = location)
        }
    }
}

@Composable
fun MyText(name: String, location: String) {
    Column {
        Text(
            text = name,
            color = Color.Cyan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = location, color = Color.LightGray, fontSize = 15.sp, fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    Column() {
        Card()
        Card()
        Card()
        Card()
        Card()
    }
}