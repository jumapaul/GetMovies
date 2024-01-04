package com.example.getmoview.ui.screens.movie_detail

import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.top_shows.TvShowItem

data class MoviesState(
    val isLoading: Boolean = false,
    val movie: MoviesEntity? = null,
    val error: String = ""
)

data class TvShowsState(
    val isLoading: Boolean = false,
    val shows: ShowsEntity? = null,
    val error: String = ""
)

data class SearchMovieState(
    val isLoading: Boolean = false,
    val movie: List<MovieDtoItem>? = null,
    val error: String = ""
)


