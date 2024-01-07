package com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout

import com.example.getmoview.ui.screens.movie.tab_layout.ComposableFun


sealed class TopRatedMoviesAndShowsTabScreens(val title: String, val screens: ComposableFun) {
    object TopRatedMovies : TopRatedMoviesAndShowsTabScreens(
        "TopMovies",
        screens = { navController -> GetTopRatedMoviesCategoryMovies(navController) })

    object TopRatedShows : TopRatedMoviesAndShowsTabScreens("TopShows", screens = { navController ->
        GetTopRatedShowsCategory(
            navController = navController
        )
    })
}