package com.example.getmoview.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.getmoview.ui.screens.setting.SettingScreen
import com.example.getmoview.ui.screens.favorite.FavoriteMoviesAndShowsScreen
import com.example.getmoview.ui.screens.movie.MovieScreen
import com.example.getmoview.ui.screens.movie_detail.MovieDetailScreen
import com.example.getmoview.ui.screens.movie_detail.ShowsDetailScreen
import com.example.getmoview.ui.screens.movies_category.popular.all.AllPopularMoviesAndTvShow
import com.example.getmoview.ui.screens.movies_category.top_rated.AllTopRatedAndTvShows
import com.example.getmoview.ui.screens.search.SearchScreen
import com.example.getmoview.ui.screens.upcoming.UpcomingMovies

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavigationGraph(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationRoutes.MovieScreen.routes,
        modifier = modifier.semantics {
            testTagsAsResourceId = true // This allow us to use test tags to find our elements
        }
    ) {

        composable(BottomNavigationRoutes.MovieScreen.routes) {
            MovieScreen(navHostController)
        }

        composable(BottomNavigationRoutes.FavoriteScreen.routes) {
            FavoriteMoviesAndShowsScreen(navHostController)
        }

        composable(BottomNavigationRoutes.SettingScreen.routes) {
            SettingScreen()
        }

        composable(BottomNavigationRoutes.MovieDetails.routes + "/{movies}") {
            MovieDetailScreen(navHostController)
        }

        composable(BottomNavigationRoutes.ShowsDetail.routes + "/{shows}"){
            ShowsDetailScreen(navController = navHostController)
        }

        composable(BottomNavigationRoutes.SearchScreen.routes){
            SearchScreen(navHostController)
        }

        composable(BottomNavigationRoutes.PopularMoviesAndTvShows.routes){
            AllPopularMoviesAndTvShow(navHostController)
        }

        composable(BottomNavigationRoutes.TopRatedMoviesAndTvShows.routes){
            AllTopRatedAndTvShows(navHostController)
        }

        composable(BottomNavigationRoutes.UpcomingMovies.routes){
            UpcomingMovies(navHostController)
        }
    }
}