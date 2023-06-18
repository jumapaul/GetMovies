package com.example.getmoview.domain.model.trending

import com.example.getmoview.data.local.entity.TrendingMoviesEntity

data class TrendingResults(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toTrendingEntity(): TrendingMoviesEntity {
        return TrendingMoviesEntity(
            adult,
            backdrop_path,
            first_air_date,
            //genre_ids,
            media_type,
            name,
           // origin_country,
            original_language,
            original_name,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count,
            id
        )
    }
}