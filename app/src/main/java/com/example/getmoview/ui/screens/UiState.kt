package com.example.getmoview.ui.screens

import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.popular_top_rated.SearchedItem

data class UiState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val error: String = ""
)

data class TrendingUiState(
    val isLoading: Boolean = false,
    val movies: List<TrendingMoviesEntity> = emptyList(),
    val error: String = ""
)

data class SearchedUiState(
    val isLoading: Boolean = false,
    val movies: List<SearchedItem> = emptyList(),
    val error: String = ""
)