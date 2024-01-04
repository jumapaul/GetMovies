package com.example.getmoview.ui.screens.genre.tab_layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.common.utils.GenreIdToName
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.view_models.MovieViewModel
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetMoviesByGenre(
    navController: NavController,
    selectedGenreIds: MutableState<List<Int>>,
    viewModel: MoviesCategoryViewModel = hiltViewModel(),
    moviesViewModel: MovieViewModel = hiltViewModel(),
    favoriteMoviesViewModel: FavoriteMoviesViewModel = hiltViewModel()
    ){

    val availableMovies = moviesViewModel.state.value


    val filteredMovies = filterMoviesByGenres(
        availableMovies.movies,
        selectedGenreIds.value
    )
    LazyColumn(modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(filteredMovies){movies->

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
                              TODO()
                    },
                    favorite = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    genreId = names
                )
            }
        }
    }
}

fun filterMoviesByGenres(
    movies: List<MoviesEntity>,
    selectedGenreIds: List<Int>
): List<MoviesEntity> {
    return movies.filter { movies ->
        selectedGenreIds.all { genreId ->
            movies.genre_ids.contains(genreId)
        }
    }
}