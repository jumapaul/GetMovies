package com.example.getmoview.ui.screens.movie.tab_layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.genre.GenreScreen
import com.example.getmoview.ui.screens.movie.Movie
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryScreen

typealias ComposableFun = @Composable (NavController) -> Unit

sealed class CategoryTabScreens(val title: String, val screens: ComposableFun) {

    object RecentMovies :
        CategoryTabScreens("Recent Movies", screens = { navController -> Movie(navController) })

    object MoviesCategory : CategoryTabScreens(
        "Movies Category",
        screens = { navController -> MoviesCategoryScreen(navController) })

    object Genre :
        CategoryTabScreens("Genre", screens = { navController -> GenreScreen(navController) })
}
