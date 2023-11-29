package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
            // Solution to the below z index is Row or Column
            Row {
                Text(text = "Hello")
                Text(text = "World")
            }
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .background(Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = "Hello")
                Text(text = "World")

                // Difference Between Alignment and Arrangement

                // Flex Box

                // same as row and column
                // Alignment -> align all the items vertically and Horizontally
                // Arrangement -> arrange the items in the vertical and horizontal and vertical alignment

                // each column and each row has main and cross axis
                // main axis the new items are always stacked
                // row -> main(Horizontal) and cross(Vertical)
                // Column -> main(Vertical) and cross(Horizontal)

                // Default
                // Here the items width and height will be based on the
                // words length will not take full page width it is based
                // on the word

                // Change Default we can use the modifier
                // and set the fillMaxSize() to stretch the column to full page

            // .fillMaxSize(0.5f) -> 0.5f -> fractional with 50% of width and height
            // or we can use width(200.dp), height(200.dp) or fillMaxHeight(1f) same for height
            }
            // Z - Index one before another
//            Text("Hello")
//            Text("World")
        }
    }
}


