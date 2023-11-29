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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

// how to style the text in compose

// res -> click new -> create a folder for font
// format all files should be in lowercase
// if there is - change it to _
// go and select any fonts from google fonts and download the file
// and do the above changes and put it inside the font folder
// then create font family for it and use it hear

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.poppins_light, FontWeight.Light),
            Font(R.font.poppins_bold, FontWeight.Bold),
            Font(R.font.poppins_extraBold, FontWeight.ExtraBold)
        )
        setContent {
             Box(modifier = Modifier
                 .fillMaxSize()
                 .background(Color(0xFF101010))) {
                 Text(
//                     text = "Jetpack Compose",
                     text = buildAnnotatedString {
                                                 withStyle(
                                                     style = SpanStyle(
                                                         color = Color.Green,
                                                         fontSize =  50.sp
                                                     )
                                                 ){
                                                     // Just giving style to J
                                                     append("J")
                                                 }
                         append("etpack") // Default Styles Applied
                         withStyle(
                             style = SpanStyle(
                                 color = Color.Green,
                                 fontSize =  50.sp
                             )
                         ){
                             // Just giving style to J
                             append("C")
                         }
                         append("ompose") // Default Styles Applied
                     },
                     color = Color.White, // DEFAULT STYLES
                     fontSize = 30.sp,
                     fontFamily = fontFamily,
                     fontWeight = FontWeight.Bold,
                     fontStyle = FontStyle.Italic,
                     textAlign = TextAlign.Center,
                     textDecoration = TextDecoration.Underline
                     )
             }
        }
    }
}

