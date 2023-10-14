package com.example.getmoview.ui.screens.genre.tab_layout

import androidx.compose.runtime.MutableState
import com.example.getmoview.ui.screens.movie.tab_layout.ComposableFun

sealed class GenreScreens(val title: String, val screens: ComposableFun) {
    data class GetMoviesByGenreId(val selectedGenreIds: MutableState<List<Int>>) : GenreScreens("Movies", screens = { navController ->
        GetMoviesByGenre(navController, selectedGenreIds)
    })

    data class GetShowsByGenreId(val selectedGenreIds: MutableState<List<Int>>) : GenreScreens("Shows", screens = { navController ->
        GetShowsByGenre(navController, selectedGenreIds)
    })
}
