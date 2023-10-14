package com.example.getmoview.ui.screens.favorite.tab_layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.favorite.FavoriteMoviesViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetFavoriteMovies(
    navController: NavController,
    viewModel: FavoriteMoviesViewModel = hiltViewModel()
) {
    val favoriteMovies = viewModel.allLocalMovies.value

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {

            items(favoriteMovies.moviesEntity) { movies ->
                val names = remember {
                    mutableStateOf<List<String>>(emptyList())
                }

                Box(modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                }) {
                    VerticalMoviesItem(
                        posterPath = movies.poster_path,
                        title = movies.title,
                        description = movies.overview,
                        date = movies.release_date,
                        onClick = {viewModel.addFavoriteMovie(movies)},
                        favorite = null,
                        genreId = names
                    )
                }

            }
        }
    }
}