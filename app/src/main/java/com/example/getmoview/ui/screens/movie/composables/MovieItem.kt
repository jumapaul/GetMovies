package com.example.getmoview.ui.screens.movie.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieItem(
    posterPath: String,
    title: String,
    date: String,
    percentage: Float,
    fontSize: TextUnit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .padding(5.dp)
    ) {
        Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500$posterPath").crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 5.dp),
            contentAlignment = Alignment.TopStart
        ) {
            CircularProgressBar(percentage = percentage, fontSize = fontSize)
        }
        Column(
            verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                color = Color.White
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                color = Color.White
            )
        }
    }
}


