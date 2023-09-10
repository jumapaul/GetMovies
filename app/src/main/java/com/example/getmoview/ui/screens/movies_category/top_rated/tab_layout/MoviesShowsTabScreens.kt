package com.example.getmoview.ui.screens.movies_category.top_rated.tab_layout

import com.example.getmoview.ui.screens.movie.tab_layout.ComposableFun


sealed class MoviesShowsTabScreens(val title: String, val screens: ComposableFun) {
    object TopMovies : MoviesShowsTabScreens(
        "TopMovies",
        screens = { navController -> GetTopMoviesCategoryMovies(navController) })

    object TopShows : MoviesShowsTabScreens("TopShows", screens = { navController ->
        GetTopRatedCategoryShows(
            navController = navController
        )
    })
}