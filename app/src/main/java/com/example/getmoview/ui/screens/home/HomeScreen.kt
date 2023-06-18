package com.example.getmoview.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getmoview.data.local.entity.MovieEntity

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.movieState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.movies) { movies ->
                PopularAndTopRatedItem(movieEntity = movies)
            }
        }
    }

}

@Composable
fun PopularAndTopRatedItem(
    movieEntity: MovieEntity
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = movieEntity.title)
        Text(text = movieEntity.movieType)
        Text(text = movieEntity.overview)
    }
}