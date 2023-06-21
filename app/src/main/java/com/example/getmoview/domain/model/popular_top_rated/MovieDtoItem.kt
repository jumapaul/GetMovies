package com.example.getmoview.domain.model.popular_top_rated

import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
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
    val vote_count: Int
) {
    fun toMovieEntity(movieType: MovieType): MovieEntity {
        return MovieEntity(
            adult,
            backdrop_path,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count,
            movieType.name, // Adding the movie type to help retrieve the different movie types
            id
        )
    }
}