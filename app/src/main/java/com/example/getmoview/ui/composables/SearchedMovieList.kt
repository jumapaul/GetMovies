package com.example.getmoview.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.movie_detail.SearchMovieState
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes
import com.example.getmoview.ui.view_models.SearchViewModel

@Composable
fun SearchedMovieList(
    state: SearchMovieState,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    if (state.movie?.isNotEmpty() == true) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(state.movie) { item ->
                item.poster_path.let {
                    SearchedMovieItem(
                        posterPath = it.ifEmpty { "https://image.tmdb.org/t/p/w500/gPbM0MK8CP8A174rmUwGsADNYKD.jpg" },
                        title = item.title,
                        date = item.release_date,
                        percentage = item.vote_average.toFloat(),
                        fontSize = 15.sp,
                        onItemClick = {
                            navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${item.id}")
                        },
                        searchedItem = item,
                        radius = 20.dp,
                    )
                }
            }
        })
    }

    val moviesNotFound = viewModel.moviesNotFound.value

    if (moviesNotFound) Text(text = "No results found")

}