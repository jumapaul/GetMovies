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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetTopMoviesCategoryMovies(
    navController: NavController,
    viewModel: MoviesCategoryViewModel = hiltViewModel()

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

//                Log.d("------->", "GetTopMoviesCategoryMovies: ${movies.id} and title ${movies.title}")
                val genres = movies.genre_ids
                val names = remember {
                    mutableStateOf<List<String>>(emptyList())
                }

                LaunchedEffect(key1 = genres) {
                    val genreNames = viewModel.getGenreNames(genres)
                    names.value = genreNames
                }

                Box(modifier = Modifier.clickable {
                    Log.d("xxxxxxx", "GetTopMoviesCategoryMovies: ${movies.id} and movies title ${movies.title}")

                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                }){
                    VerticalMoviesItem(
                        posterPath = movies.poster_path,
                        title = movies.title,
                        description = movies.overview,
                        date = movies.release_date,
                        genreId = names
                    )
                }
            }
        }
    }
}