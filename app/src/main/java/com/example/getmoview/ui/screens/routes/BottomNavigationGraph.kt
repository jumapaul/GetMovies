package com.example.getmoview.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.getmoview.ui.screens.account.AccountScreen
import com.example.getmoview.ui.screens.favorite.FavoriteScreen
import com.example.getmoview.ui.screens.movie.MovieScreen
import com.example.getmoview.ui.screens.movie_detail.MovieDetailScreen

@Composable
fun BottomNavigationGraph(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationRoutes.MovieScreen.routes
    ) {

        composable(BottomNavigationRoutes.MovieScreen.routes) {
            MovieScreen(navHostController)
        }

        composable(BottomNavigationRoutes.FavoriteScreen.routes) {
            FavoriteScreen()
        }

        composable(BottomNavigationRoutes.AccountScreen.routes) {
            AccountScreen()
        }

        composable(BottomNavigationRoutes.MovieDetails.routes + "/{popularAndTopRated}") {
            MovieDetailScreen(navHostController)
        }

//        composable(BottomNavigationRoutes.TrendingMovieDetails.routes + "/{trending}") {
//            MovieDetailScreen()
//        }
    }
}