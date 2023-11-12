package com.example.getmoview.ui.screens.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationRoutes(val title: String?, var icon: ImageVector?, val routes: String) {
    object MovieScreen : BottomNavigationRoutes("Movie", Icons.Default.PlayArrow, "movie")
    object FavoriteScreen : BottomNavigationRoutes("Favorite", Icons.Default.Favorite, "favorites")
    object SettingScreen : BottomNavigationRoutes("Account", Icons.Default.Settings, "account")
    object MovieDetails: BottomNavigationRoutes(null, null, "movie_detail")
    object ShowsDetail: BottomNavigationRoutes(null, null, "top_rated")
    object SearchScreen: BottomNavigationRoutes(null, null, "search_screen")
    object PopularMoviesAndTvShows: BottomNavigationRoutes(null, null, "popular")
    object TopRatedMoviesAndTvShows: BottomNavigationRoutes(null, null, "topRated")
    object UpcomingMovies: BottomNavigationRoutes(null, null, "upcoming")
}
