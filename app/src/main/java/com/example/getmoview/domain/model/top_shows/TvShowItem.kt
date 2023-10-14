package com.example.getmoview.domain.model.top_shows

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
){
    fun toShowEntity(): ShowsEntity {
        return ShowsEntity(
            backdrop_path,
            first_air_date,
            id,
            name,
            original_language,
            original_name,
            overview,
            popularity,
            poster_path,
            vote_average,
            vote_count
        )
    }
}