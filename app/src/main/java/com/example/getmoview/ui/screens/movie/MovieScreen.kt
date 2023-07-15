package com.example.getmoview.ui.screens.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.movie.composables.Movies
import com.example.getmoview.ui.screens.movie.composables.SearchBar
import com.example.getmoview.ui.screens.movie.composables.SearchedMovieList
import kotlinx.coroutines.delay

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val searchedMovies = viewModel.searchedMovies.value
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

            LaunchedEffect(searchTerm.value) {
                delay(1000)
                viewModel.getSearchedMovies(searchTerm.value)
            }

            SearchBar(searchTerm = searchTerm)

            if (searchTerm.value.isEmpty()) Movies(navController) else SearchedMovieList(
                state = searchedMovies
            )
        }
    }
}
