package com.example.getmoview.domain.model.top_shows

import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.ShowsEntity

data class TvShowItem(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toShowEntity(categoryType: CategoryType): ShowsEntity {
        return ShowsEntity(
            id = id,
            first_air_date = first_air_date,
            genre_ids = genre_ids,
            name = name,
            overview = overview,
            poster_path = poster_path,
            vote_average = vote_average,
            categoryType = categoryType
        )
    }
}