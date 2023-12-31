package com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.common.utils.GenreIdToName
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel

@Composable
fun GetTopRatedMoviesCategoryMovies(
    navController: NavController,
    viewModel: MoviesCategoryViewModel = hiltViewModel(),

) {
    val movies = viewModel.topRatedMoviesState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.movies) { movies ->

                val names = remember {
                    mutableStateOf<List<String>>(emptyList())
                }
                GenreIdToName(genres = movies.genre_ids, names)

                var isFavorite by remember {
                    mutableStateOf(false)
                }

                Box(modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                }) {
                    VerticalMoviesItem(
                        posterPath = movies.poster_path,
                        title = movies.title,
                        description = movies.overview,
                        date = movies.release_date,
                        onClick = {
                            isFavorite = !isFavorite
                        },
                        isFavorite = isFavorite,
                        genreId = names
                    )
                }
            }
        }
    }
}