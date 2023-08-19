package com.example.getmoview.ui.screens.movie.tab_layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.a_z.AtoZScreen
import com.example.getmoview.ui.screens.genre.GenreScreen
import com.example.getmoview.ui.screens.language.LanguageScreen
import com.example.getmoview.ui.screens.movie.Movie
import com.example.getmoview.ui.screens.movies_category.MoviesCategoryScreen
import com.example.getmoview.ui.screens.year.YearScreen

typealias ComposableFun = @Composable (NavController) -> Unit

sealed class TabScreens(val title: String, val screens: ComposableFun) {

    object RecentMovies : TabScreens("Recent Movies", screens = { navController -> Movie(navController) })
    object MoviesCategory : TabScreens("Movies Category", screens = { MoviesCategoryScreen() })
    object Genre : TabScreens("Genre", screens = { GenreScreen() })
    object Year : TabScreens("Year", screens = { YearScreen() })
    object Language : TabScreens("Language", screens = { LanguageScreen() })
    object AtoZ : TabScreens("A-Z", screens = { AtoZScreen() })
}
