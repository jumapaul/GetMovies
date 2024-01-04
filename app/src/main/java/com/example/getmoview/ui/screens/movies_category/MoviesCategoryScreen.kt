package com.example.getmoview.ui.screens.movies_category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.getmoview.R
import com.example.getmoview.ui.composables.AutoSliding
import com.example.getmoview.ui.composables.MovieItemsWithoutRating
import com.example.getmoview.ui.composables.MyTexts
import com.example.getmoview.ui.composables.OtherMoviesItem
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes
import com.example.getmoview.ui.view_models.MoviesCategoryViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MoviesCategoryScreen(
    navController: NavController,
    viewModel: MoviesCategoryViewModel = hiltViewModel()
) {
    val upcomingMoviesState = viewModel.upcomingMoviesState.value
    val topRatedMoviesState = viewModel.topRatedMoviesState.value
    val topRatedShows = viewModel.topRatedShowsState.value
    val popularMovies = viewModel.popularMovies.value
    val popularTvShows = viewModel.popularTvShows.value

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {

        Column {
            TitleAlignment(
                text1 = "Upcoming Movies",
                text2 = "See all",
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.UpcomingMovies.routes)
                })

            AutoSliding(
                modifier = Modifier,
                itemsCount = upcomingMoviesState.movies.size
            ) { index ->
                val items = upcomingMoviesState.movies[index]

                Box(modifier = Modifier.clickable {

                    navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${items.id}")
                }) {
                    MovieItemsWithoutRating(
                        posterPath = items.poster_path,
                        title = items.title,
                        date = items.release_date
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(top = 5.dp)) {
            TitleAlignment(text1 = "Top Rated", text2 = "Movies", modifier = Modifier.clickable {
                navController.navigate(BottomNavigationRoutes.TopRatedMoviesAndTvShows.routes)
            })
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(topRatedMoviesState.movies) { movie ->

                    Box(modifier = Modifier.clickable {
                        navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movie.id}")
                    }) {
                        OtherMoviesItem(
                            posterPath = movie.poster_path,
                            title = movie.title,
                            date = movie.release_date,
                            percentage = movie.vote_average.toFloat(),
                            fontSize = 18.sp,
                            radius = 20.dp,
                            modifier = Modifier
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(top = 5.dp)) {
            TitleAlignment(
                text1 = "Top rated shows",
                text2 = "Tv Shows",
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.TopRatedMoviesAndTvShows.routes)
                })

            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(topRatedShows.shows) { shows ->
                    Box(modifier = Modifier.clickable {
                        navController.navigate(BottomNavigationRoutes.ShowsDetail.routes + "/${shows.id}")
                    }) {
                        OtherMoviesItem(
                            posterPath = shows.poster_path,
                            title = shows.name,
                            date = shows.first_air_date,
                            percentage = shows.vote_average.toFloat(),
                            fontSize = 18.sp,
                            radius = 20.dp,
                            modifier = Modifier
                        )
                    }
                }

            }
        }

        Column(modifier = Modifier.padding(top = 5.dp)) {
            TitleAlignment(
                text1 = "Popular Movies",
                text2 = "Movies",
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.PopularMoviesAndTvShows.routes)
                })

            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(popularMovies.movies) { movies ->

                    Box(modifier = Modifier.clickable {
                        navController.navigate(BottomNavigationRoutes.MovieDetails.routes + "/${movies.id}")

                    }) {
                        OtherMoviesItem(
                            posterPath = movies.poster_path,
                            title = movies.title,
                            date = movies.release_date,
                            percentage = movies.vote_average.toFloat(),
                            fontSize = 18.sp,
                            radius = 20.dp,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.padding(top = 5.dp)) {
            TitleAlignment(
                text1 = "Popular Tv Shows",
                text2 = "Tv Shows",
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavigationRoutes.PopularMoviesAndTvShows.routes)
                })

            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(popularTvShows.shows) { tvShowItem ->

                    Box(modifier = Modifier.clickable {
                        navController.navigate(BottomNavigationRoutes.ShowsDetail.routes + "/${tvShowItem.id}")
                    }) {
                        OtherMoviesItem(
                            posterPath = tvShowItem.poster_path,
                            title = tvShowItem.name,
                            date = tvShowItem.first_air_date,
                            percentage = tvShowItem.vote_average.toFloat(),
                            fontSize = 18.sp,
                            radius = 20.dp,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TitleAlignment(
    text1: String,
    text2: String,
    modifier: Modifier
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyTexts(
                text = text1,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
            MyTexts(
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier,
                color = colorResource(id = R.color.teal_700)
            )
        }

        MyTexts(
            text = text2,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
        )

    }
}
