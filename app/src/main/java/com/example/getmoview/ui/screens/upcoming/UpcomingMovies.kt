package com.example.getmoview.ui.screens.upcoming

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.common.utils.GenreIdToName
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun UpcomingMovies(
    navController: NavController,
    viewModel: MoviesCategoryViewModel = hiltViewModel(),
    favoriteMoviesViewModel: FavoriteMoviesViewModel = hiltViewModel()
) {

    val upcoming = viewModel.upcomingMoviesState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.MovieScreen.routes)
                })
            Text(
                text = "Upcoming Movies",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(upcoming.movies) { item ->

                val names = remember {
                    mutableStateOf<List<String>>(emptyList())
                }
                GenreIdToName(genres = item.genre_ids, names)
//                val genres = item.genre_ids
//
//                val names = remember {
//                    mutableStateOf<List<String>>(emptyList())
//                }
//                LaunchedEffect(key1 = genres) {
//                    val genreName = viewModel.getGenreNames(genres)
//                    names.value = genreName
//                }

                var isFavorite by remember {
                    mutableStateOf(false)
                }

                if (item.isFavorite) isFavorite = true
                Box(modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${item.id}")
                }) {
                    VerticalMoviesItem(
                        posterPath = item.poster_path,
                        title = item.title,
                        description = item.overview,
                        date = item.release_date,
                        onClick = {
                                  TODO()
                        },
                        isFavorite = isFavorite,
                        genreId = names
                    )
                }
            }
        }
    }

}