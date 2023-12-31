package com.example.getmoview.ui.screens.favorite.tab_layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.screens.favorite.NoFavorite
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

        if (favoriteMovies.moviesEntity.isEmpty()) {
            NoFavorite(text = "No favorite movies")


        } else {
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {

                items(favoriteMovies.moviesEntity) { movies ->
                    val names = remember {
                        mutableStateOf<List<String>>(emptyList())
                    }

                    Box(modifier = Modifier
                        .clickable {
                            navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                        }
                        .fillMaxSize(), contentAlignment = Alignment.Center) {

                        VerticalMoviesItem(
                            posterPath = movies.poster_path,
                            title = movies.title,
                            description = movies.overview,
                            date = movies.release_date,
                            isFavorite = true,
                            onClick = {
                                navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                            },
                            genreId = names
                        )
                    }

                }
            }
        }
    }
}