package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.getmoview.ui.screens.TrendingUiState
import com.example.getmoview.ui.screens.UiState

@Composable
fun PopularAndTopRatedMoviesCard(state: UiState) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .height(160.dp)){
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items(state.movies) { movies ->
                MovieItem(modifier = Modifier, posterPath = movies.poster_path)
            }
        }

        if (state.error.isNotBlank()){
            Text(text = state.error)
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun TrendingMoviesCard(state: TrendingUiState){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .height(160.dp)){
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(state.movies) { movies ->
                MovieItem(modifier = Modifier, posterPath = movies.poster_path)
            }
        }

        if (state.error.isNotBlank()){
            Text(text = state.error)
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}