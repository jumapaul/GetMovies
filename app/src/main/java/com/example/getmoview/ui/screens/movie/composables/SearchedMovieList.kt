package com.example.getmoview.ui.screens.movie.composables

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.SearchedUiState
import com.example.getmoview.ui.screens.movie.MovieViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes
import kotlinx.coroutines.delay

@Composable
fun SearchedMovieList(
    state: SearchedUiState,
    viewModel: MovieViewModel = hiltViewModel(),
    navController: NavController
) {

    if (state.movies.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(state.movies) { item ->
                item.poster_path?.let {
                    SearchedMovieItem(
                        posterPath = it,
                        title = item.title,
                        date = item.release_date,
                        percentage = item.vote_average.toFloat(),
                        fontSize = 15.sp,
                        onItemClick = {
                            navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${item.id}")
                            Log.d("---------->", "SearchedMovieList: $item")
                        },
                        searchedItem = item,
                        radius = 20.dp
                    )
                }
            }
        })
    } else {
        LaunchedEffect(state.movies) {
            delay(3000)

            viewModel.moviesNotFound(true)
        }
    }

    val moviesNotFound = viewModel.moviesNotFound.value

    if (moviesNotFound) Text(text = "No results found")

}