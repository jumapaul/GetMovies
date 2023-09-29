package com.example.getmoview.domain.model.top_shows

data class TvShowDto(
    val page: Int,
    val results: List<TvShowItem>,
    val total_pages: Int,
    val total_results: Int
)