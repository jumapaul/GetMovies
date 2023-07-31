package com.example.getmoview.ui.screens.routes

import com.example.getmoview.R

sealed class BottomNavigationRoutes(val title: String?, var icon: Int?, val routes: String) {
    object MovieScreen : BottomNavigationRoutes("Movie", R.drawable.movie, "movie")
    object FavoriteScreen : BottomNavigationRoutes("Favorite", R.drawable.favorite, "favorites")
    object AccountScreen : BottomNavigationRoutes("Account", R.drawable.account, "account")
    object MovieDetails: BottomNavigationRoutes(null, null, "movie_detail")
    object TrendingMovieDetails: BottomNavigationRoutes(null, null, "trending_movies")
}
