package com.example.getmoview.ui.screens.movie

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getmoview.ui.screens.movie.composables.HeaderTexts
import com.example.getmoview.ui.screens.movie.composables.PopularAndTopRatedMoviesCard
import com.example.getmoview.ui.screens.movie.composables.SearchBar
import com.example.getmoview.ui.screens.movie.composables.TrendingMoviesCard

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {
    val popularMovies = viewModel.popularMovies.value
    val topRatedMovies = viewModel.topRatedMovies.value
    val trendingMovies = viewModel.trendingMovieState.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            val searchTerm = remember {
                mutableStateOf("")
            }

            SearchBar(searchTerm = searchTerm)

            HeaderTexts(text = "Popular Movies")

            PopularAndTopRatedMoviesCard(state = popularMovies)

            HeaderTexts(text = "Top Rated Movies")

            PopularAndTopRatedMoviesCard(state = topRatedMovies)

            HeaderTexts(text = "Trending Movies")

            TrendingMoviesCard(state = trendingMovies)
        }
    }
}
