package com.example.getmoview.ui.screens.movie_detail

import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.Results

data class PopularAndTopRatedMovieState(
    val isLoading: Boolean = false,
    val movie: MovieDtoItem? = null,
    val error: String = ""
)

data class TrendingMovieState(
    val isLoading: Boolean = false,
    val movie: Results? = null,
    val error: String = ""
)

data class SearchMovieState(
    val isLoading: Boolean = false,
    val movie: List<MovieDtoItem>? = null,
    val error: String = ""
)


