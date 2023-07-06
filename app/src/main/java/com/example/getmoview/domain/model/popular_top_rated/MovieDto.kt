package com.example.getmoview.domain.model.popular_top_rated

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val page: Int,
    val results: List<MovieDtoItem>,
    val total_pages: Int,
    val total_results: Int
)

data class SearchedDto(
    val page: Int,
    val results: List<SearchedItem>,
    val total_pages: Int,
    val total_results: Int
)
