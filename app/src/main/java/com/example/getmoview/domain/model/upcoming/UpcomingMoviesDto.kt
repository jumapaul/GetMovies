package com.example.getmoview.domain.model.upcoming

import com.example.getmoview.domain.model.MovieDtoItem

data class UpcomingMoviesDto(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDtoItem>,
    val total_pages: Int,
    val total_results: Int
)