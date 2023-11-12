package com.example.getmoview.domain.model

import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.MoviesEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieDtoItem(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
) {
    fun toMovieEntity(categoryType: CategoryType): MoviesEntity {
        return MoviesEntity(
            id = id,
            genre_ids = genre_ids,
            overview = overview,
            poster_path = poster_path,
            release_date = release_date,
            title = title,
            vote_average = vote_average,
            categoryType = categoryType
        )
    }
}
