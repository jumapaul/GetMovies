package com.example.getmoview.domain.model.trending

data class Trending(
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
)