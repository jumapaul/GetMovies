package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieItem(
    modifier: Modifier,
    posterPath: String
) {
    Box(modifier = modifier.fillMaxSize().background(
        MaterialTheme.colorScheme.surface)) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${posterPath}", contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.padding(5.dp).background(
                MaterialTheme.colorScheme.surface,
//                RoundedCornerShape(20.dp)
            )
        )
    }
}