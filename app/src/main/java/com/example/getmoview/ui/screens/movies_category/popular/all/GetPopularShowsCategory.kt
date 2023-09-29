package com.example.getmoview.ui.screens.movies_category.popular.all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetPopularShowsCategory(
    navController: NavController,
    viewModel: MoviesCategoryViewModel = hiltViewModel()
) {

    val shows = viewModel.popularTvShows.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(shows.movies) { shows ->
                val genre = shows.genre_ids
                val names = remember {
                    mutableStateOf<List<String>>(emptyList())
                }
                LaunchedEffect(key1 = genre) {
                    val genreNames = viewModel.getGenreNames(genre)
                    names.value = genreNames
                }

                Box(modifier = Modifier.clickable {
                    navController.navigate(
                        BottomNavigationRoutes.ShowsDetail.routes + "/${shows.id}"
                    )
                }) {
                    VerticalMoviesItem(
                        posterPath = shows.poster_path,
                        title = shows.name,
                        description = shows.overview,
                        date = shows.first_air_date,
                        genreId = names
                    )
                }
            }
        }
    }

}