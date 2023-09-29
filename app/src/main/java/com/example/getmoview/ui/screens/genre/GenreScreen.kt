package com.example.getmoview.ui.screens.genre

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.common.utils.GenreIdToName
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.genre.Genre
import com.example.getmoview.ui.composables.ExpandButton
import com.example.getmoview.ui.composables.ListingItem
import com.example.getmoview.ui.composables.VerticalMoviesItem
import com.example.getmoview.ui.screens.movie.MovieViewModel
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun GenreScreen(
    navController: NavController,
    moviesViewModel: MovieViewModel = hiltViewModel(),
    viewModel: MoviesCategoryViewModel = hiltViewModel(),

    ) {
    val availableMovies = moviesViewModel.state.value
    val selectedGenreIds = remember {
        mutableStateOf(emptyList<Int>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        GenreItem(selectedGenreIds)

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
                Box(modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                }) {
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


@Composable
fun GenreItem(
    selectedGenreIds: MutableState<List<Int>>
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "See all genre",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
            ExpandButton(expanded = expanded, onClick = {
                expanded = !expanded
            })
        }
        if (expanded) {
            ExpandedItemList(selectedGenreIds = selectedGenreIds)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandedItemList(
    viewModel: GenreViewModel = hiltViewModel(),
    selectedGenreIds: MutableState<List<Int>>
) {
    val state = viewModel.genre.value

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        state.genre.forEach { genre ->
            ListingItem(
                genre = genre,
                selectedGenreIds = selectedGenreIds
            )
        }
    }
}

fun filterMoviesByGenres(
    movies: List<MovieDtoItem>,
    selectedGenreIds: List<Int>
): List<MovieDtoItem> {
    return movies.filter { movies ->
        selectedGenreIds.all { genreId ->
            movies.genre_ids.contains(genreId)
        }
    }
}