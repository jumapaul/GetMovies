package com.example.getmoview.ui.screens.movies_category.popular.tab_layout

import com.example.getmoview.ui.screens.movie.tab_layout.ComposableFun
import com.example.getmoview.ui.screens.movies_category.popular.all.GetPopularMoviesCategory
import com.example.getmoview.ui.screens.movies_category.popular.all.GetPopularShowsCategory

sealed class PopularMoviesShowTabScreens(val title: String, val screens: ComposableFun){
    object PopularShows : PopularMoviesShowTabScreens("PopularShows", screens = { navController ->
        GetPopularShowsCategory(
            navController = navController
        )
    })

    object PopularMovies : PopularMoviesShowTabScreens("PopularMovies", screens = { navController ->
        GetPopularMoviesCategory(
            navController = navController
        )
    })
}