package com.example.getmoview.ui.screens.movie.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.screens.movie.MovieViewModel

@Composable
fun Movies(
    viewModel: MovieViewModel = hiltViewModel()
){
    val popularMovies = viewModel.popularMovies.value
    val topRatedMovies = viewModel.topRatedMovies.value
    val trendingMovies = viewModel.trendingMovieState.value

    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start,
    modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        ) {
        HeaderTexts(text = "Popular Movies",
        )

        PopularAndTopRatedMoviesCard(state = popularMovies)

        HeaderTexts(text = "Top Rated Movies")

        PopularAndTopRatedMoviesCard(state = topRatedMovies)

        HeaderTexts(text = "Trending Movies")

        TrendingMoviesCard(state = trendingMovies)
    }
}