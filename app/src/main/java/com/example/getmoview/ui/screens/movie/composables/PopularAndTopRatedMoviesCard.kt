package com.example.getmoview.ui.screens.movie.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.TrendingUiState
import com.example.getmoview.ui.screens.UiState
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun PopularAndTopRatedMoviesCard(
    state: UiState,
    navController: NavController

) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(160.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items(state.movies) { movies ->
                PopularAndTopRatedMovieItem(
                    posterPath = movies.poster_path,
                    title = movies.title,
                    date = movies.release_date,
                    percentage = movies.vote_average.toFloat(),
                    fontSize = 15.sp,
                    onItemClick = {
                        navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                    },
                    movieEntity = movies,
                    radius = 20.dp
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(text = state.error)
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun TrendingMoviesCard(
    state: TrendingUiState,
    navController: NavController

) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(160.dp),
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(state.movies) { movies ->
                Box(modifier = Modifier.fillMaxSize()) {
                    TrendingMovieItem(
                        posterPath = movies.poster_path,
                        title = movies.title.orEmpty(),
                        date = movies.release_date.orEmpty(),
                        percentage = movies.vote_average.toFloat(),
                        fontSize = 15.sp,
                        onItemClick = {
                            navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")
                            Log.d("=========>", "TrendingMoviesCard: ${movies.id}")
                        },
                        trendingMoviesEntity = movies,
                        radius = 20.dp
                    )
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(text = state.error)
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}