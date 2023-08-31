package com.example.getmoview.ui.ui_states

import com.example.getmoview.domain.model.genre.Genre
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.top_rated_shows.TopRatedShowItem

data class MovieUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieDtoItem> = emptyList(),
    var page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,

    )

data class TvShowUiState(
    val isLoading: Boolean = false,
    val movies: List<TopRatedShowItem> = emptyList(),
    var page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,

    )

data class GenreUiState(
    val isLoading: Boolean = false,
    val genre: List<Genre> = emptyList(),
    val error: String? = null
)
