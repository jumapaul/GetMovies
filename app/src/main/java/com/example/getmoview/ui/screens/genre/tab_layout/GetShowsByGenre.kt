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
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.view_models.FavoriteMoviesViewModel
import com.example.getmoview.ui.view_models.ShowsByGenreViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GetShowsByGenre(
    navController: NavController,
    selectedGenreIds: MutableState<List<Int>>,
    viewModel: ShowsByGenreViewModel = hiltViewModel(),
    favoriteMoviesViewModel: FavoriteMoviesViewModel = hiltViewModel()
) {

    val availableShows = viewModel.tvShows.value

    val filteredShows = filterShowsByGenres(
        availableShows.shows,
        selectedGenreIds.value
    )

    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(filteredShows) { show ->
            val names = remember {
                mutableStateOf<List<String>>(emptyList())
            }
            GenreIdToName(genres = show.genre_ids, names)

            var isFavorite by remember {
                mutableStateOf(false)
            }
            Box(modifier = Modifier.clickable {
                navController.navigate(BottomNavigationRoutes.ShowsDetail.routes + "/${show.id}")
            }) {
                VerticalMoviesItem(
                    posterPath = show.poster_path,
                    title = show.name,
                    description = show.overview,
                    date = show.first_air_date,
                    isFavorite = isFavorite,
                    onClick = {
//                              TODO()
                    },
                    genreId = names
                )
            }
        }
    }
}


fun filterShowsByGenres(
    movies: List<ShowsEntity>,
    selectedGenreIds: List<Int>
): List<ShowsEntity> {
    return movies.filter { showsEntity ->
        selectedGenreIds.all { genreId ->
            showsEntity.genre_ids.contains(genreId)
        }
    }
}