package com.example.getmoview.ui.ui_states

import com.example.getmoview.domain.model.genre.Genre
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.Results

data class UiState(
    val isLoading: Boolean = false,
    val movies: List<MovieDtoItem> = emptyList(),
    var page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,

    )
data class TrendingUiState(
    val isLoading: Boolean = false,
    val movies: List<Results> = emptyList(),
    val error: String? = null
)

data class GenreUiState(
    val isLoading: Boolean = false,
    val genre: List<Genre> = emptyList(),
    val error: String? = null
)

