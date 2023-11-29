package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         // Appearance, sizing, Change Layout, Add Information, Composable Interactive(Like Clickable etc) -> using modifier
            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxSize(0.5f)
                    .width(600.dp) // this will adjust to screen if screen is 300 and width is 600 it will adjust to 300
//                    .requiredWidth(600.dp) // this will go beyond the screen size -> like width and max width
                    .padding(600.dp)
                    .border(5.dp, Color.Magenta)
                    .padding(5.dp)
                    .border(5.dp, Color.Black)
            ) {
              Text(text = "Hello", modifier = Modifier.offset(50.dp, 20.dp))
                Spacer(modifier = Modifier.height(50.dp))
                Text("World", Modifier.clickable {  })
            }
            // offset - start from top left corner of the composable
            // offset - moving away from line

            // offset - it will overlap
            // Spacer - move the text down a little
            // we can apply the same property again and again

            // we can add effect using Modifier -> Modifier.clickable {  }
        }
    }
}


