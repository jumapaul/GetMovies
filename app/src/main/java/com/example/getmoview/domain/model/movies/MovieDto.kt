package com.example.getmoview.domain.model.movies

import com.example.getmoview.domain.model.MovieDtoItem
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val page: Int,
    val results: List<MovieDtoItem>,
    val total_pages: Int,
    val total_results: Int
)
