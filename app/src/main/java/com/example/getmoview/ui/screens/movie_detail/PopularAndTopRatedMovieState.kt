package com.example.getmoview.ui.screens.movie_detail

import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.TrendingMoviesEntity

data class PopularAndTopRatedMovieState(
    val isLoading: Boolean = false,
    val movie: MovieEntity? = null,
    val error: String = ""
)

data class TrendingMovieState(
    val isLoading: Boolean = false,
    val movie: TrendingMoviesEntity? = null,
    val error: String = ""
)


