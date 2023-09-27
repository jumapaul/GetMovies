package com.example.getmoview.ui.screens.routes

import com.example.getmoview.R

sealed class BottomNavigationRoutes(val title: String?, var icon: Int?, val routes: String) {
    object MovieScreen : BottomNavigationRoutes("Movie", R.drawable.movie, "movie")
    object FavoriteScreen : BottomNavigationRoutes("Favorite", R.drawable.favorite, "favorites")
    object AccountScreen : BottomNavigationRoutes("Account", R.drawable.account, "account")
    object WatchListScreen : BottomNavigationRoutes("WatchList", R.drawable.baseline_bookmarks, "bookmarks")
    object MovieDetails: BottomNavigationRoutes(null, null, "movie_detail")
    object ShowsDetail: BottomNavigationRoutes(null, null, "top_rated")
    object SearchScreen: BottomNavigationRoutes(null, null, "search_screen")
    object PopularMoviesAndTvShows: BottomNavigationRoutes(null, null, "popular")
    object TopRatedMoviesAndTvShows: BottomNavigationRoutes(null, null, "topRated")
    object UpcomingMovies: BottomNavigationRoutes(null, null, "upcoming")
}
