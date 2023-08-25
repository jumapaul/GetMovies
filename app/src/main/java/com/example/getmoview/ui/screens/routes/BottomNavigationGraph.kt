package com.example.getmoview.ui.screens.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.getmoview.ui.screens.search.SearchScreen
import com.example.getmoview.ui.screens.account.AccountScreen
import com.example.getmoview.ui.screens.favorite.FavoriteScreen
import com.example.getmoview.ui.screens.movie.MovieScreen
import com.example.getmoview.ui.screens.movie_detail.MovieDetailScreen
import com.example.getmoview.ui.screens.watchlist_screen.WatchListScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavigationGraph(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationRoutes.MovieScreen.routes,
        modifier = Modifier.semantics {
            testTagsAsResourceId = true // This allow us to use test tags to find our elements
        }
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

        composable(BottomNavigationRoutes.WatchListScreen.routes){
            WatchListScreen()
        }

        composable(BottomNavigationRoutes.MovieDetails.routes + "/{popularAndTopRated}") {
            MovieDetailScreen(navHostController)
        }

        composable(BottomNavigationRoutes.SearchScreen.routes){
            SearchScreen(navHostController)
        }
    }
}