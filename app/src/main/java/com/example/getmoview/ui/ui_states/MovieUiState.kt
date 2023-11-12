package com.example.getmoview.ui.ui_states

import com.example.getmoview.data.local.GenresIdsEntity
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity

data class MovieUiState(
    val isLoading: Boolean = false,
    val movies: List<MoviesEntity> = emptyList(),
    var page: Int = 1,
    val endReached: Boolean = false,
    var error: String = "",

    )

data class TvShowUiState(
    val isLoading: Boolean = false,
    val shows: List<ShowsEntity> = emptyList(),
    var page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,

    )

data class GenreUiState(
    val isLoading: Boolean = false,
    val genre: List<GenresIdsEntity> = emptyList(),
    val error: String? = null
)

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val moviesEntity: List<MoviesEntity> = emptyList(),
    val error: String? = null
)

data class FavoriteShowsUiState(
    val isLoading: Boolean = false,
    val showsEntity: List<ShowsEntity> = emptyList(),
    val error: String? = null
)

