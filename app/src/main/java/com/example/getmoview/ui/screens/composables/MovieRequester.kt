package com.example.getmoview.ui.screens.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.getmoview.R

@Composable
fun MovieRequester(
    posterPath: String
){
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = ImageRequest.Builder(LocalContext.current)
            .data(
                if (posterPath.isNotEmpty()) {
                    "https://image.tmdb.org/t/p/w500$posterPath"
                } else {
                    R.drawable.empty
                }
            ).crossfade(true).build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}