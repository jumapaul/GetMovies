package com.example.getmoview.domain.model.top_rated_shows

data class TopRatedTvShowDto(
    val page: Int,
    val results: List<TopRatedShowItem>,
    val total_pages: Int,
    val total_results: Int
)