package com.example.getmoview.ui.screens.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.composables.CategoriesItem
import com.example.getmoview.ui.screens.composables.MovieItems
import com.example.getmoview.ui.screens.composables.SearchBar
import com.example.getmoview.ui.screens.composables.SearchedMovieList
import kotlinx.coroutines.delay

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {

    val searchedMovies = viewModel.searchedMovies.value
    val state = viewModel.state.value


    Box(modifier = Modifier.fillMaxSize()) {
        if (state.movies.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {

                val searchTerm = remember {
                    mutableStateOf("")
                }

                LaunchedEffect(searchTerm.value) {
                    delay(1000)
                    viewModel.getSearchedMovies(searchTerm.value)
                }

                SearchBar(searchTerm = searchTerm)

                if (searchTerm.value.isEmpty()) Movie() else SearchedMovieList(
                    state = searchedMovies, navController = navController
                )

            }
        }

        if (state.error?.isNotBlank() == true) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                //                textAlign = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}

@Composable
fun Movie(
    viewModel: MovieViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    val categories = listOf(
        "Recent Movies",
        "Movies category",
        "Genre",
        "Year",
        "Language",
        "A-Z",
    )


    Box(modifier = Modifier.fillMaxSize()) {
        if (state.movies.isNotEmpty()) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(categories) { categories ->
                        CategoriesItem(category = categories)
                    }
                }

                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(state.movies.size) { movies ->
                        val item = state.movies[movies]
                        if (movies >= (state.movies.size - 1) && !state.endReached && state.isLoading) {
                            viewModel.loadMovies()
                        }

                        Box(modifier = Modifier.padding(5.dp)) {
                            MovieItems(
                                posterPath = item.poster_path,
                                title = item.title,
                                date = item.release_date,
                                percentage = item.vote_average.toFloat(),
                                fontSize = 18.sp,
                                radius = 20.dp
                            )
                        }
                    }
                }
                )
            }
        }
    }
}



