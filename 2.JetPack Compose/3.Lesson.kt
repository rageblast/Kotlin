package com.example.compose

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          val painter = painterResource(id = R.drawable.pineapple)
            val description = "Pineapple in Grass"
            val title = "Pineapple in Grass"
            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp)
            ) {
                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title)
            }
        }
    }
}

// We will create a composable which will help us to reusable for other images also
// composable function start with Capital letter Normal function won't
// Painter allows us to add images
// modifier: Modifier = Modifier -> set the default value to Modifier
// see the elevation property -> we are using material 3 so we use like it
// Box will stack all the items -> what ever we put first will be stacked at last
// contentScale = ContentScale.Crop -> crop image set it at the middle, top

// startY - From which position the gradient should start
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        // Your card content goes here
        Box(modifier = Modifier.height(200.dp)) {
          Image(
              painter = painter,
              contentDescription = contentDescription,
              contentScale = ContentScale.Crop
          )
          Box(modifier = Modifier
              .fillMaxSize()
              .background(
                  Brush.verticalGradient(
                      colors = listOf(
                          Color.Transparent,
                          Color.Black
                      ),
                      startY = 300f
                  )
              )
          )
          Box(
              modifier = Modifier
                  .fillMaxSize()
                  .padding(12.dp),
                  contentAlignment = Alignment.BottomStart
          )  {
              Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
          }
        }
    }
}