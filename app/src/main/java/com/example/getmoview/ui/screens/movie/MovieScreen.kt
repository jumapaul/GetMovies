package com.example.getmoview.ui.screens.movie

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.getmoview.ui.screens.SearchedUiState
import com.example.getmoview.ui.screens.movie.composables.HeaderTexts
import com.example.getmoview.ui.screens.movie.composables.Movies
import com.example.getmoview.ui.screens.movie.composables.PopularAndTopRatedMoviesCard
import com.example.getmoview.ui.screens.movie.composables.SearchBar
import com.example.getmoview.ui.screens.movie.composables.SearchedMovieList
import com.example.getmoview.ui.screens.movie.composables.TrendingMoviesCard
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val searchedMovies = viewModel.searchedNews.value

    Log.d("---------------->", "MovieScreen: $searchedMovies")
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

            LaunchedEffect(searchTerm.value){
                delay(1000)
                viewModel.getSearchedMovies(searchTerm.value)

                if (viewModel.searchedNews.value.movies.isEmpty()){
                    viewModel.moviesNotFound(true)
                }
            }

            val moviesNotFound = viewModel.moviesNotFound.value

            if (moviesNotFound) Text(text = "No results found")

            SearchBar(searchTerm = searchTerm)

            if (searchTerm.value.isEmpty()) Movies() else SearchedMovieList(
                state = searchedMovies
            )
        }
    }
}
