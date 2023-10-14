package com.example.getmoview.ui.screens.favorite.tab_layout

import com.example.getmoview.ui.screens.movie.tab_layout.ComposableFun

sealed class FavoritesScreens (val title: String, val screens: ComposableFun){
    object GetFavoriteMovies: FavoritesScreens("Movies", screens = { navController ->
        GetFavoriteMovies(navController)
    })

    object GetFavoriteShows: FavoritesScreens("Shows", screens = { navController ->
        GetFavoriteShows(navController)
    })
}