package com.example.getmoview.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("movies")
data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String,
    //val genre_ids: List<Int>,
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
    val movieType: String,  // We add this parameter to our entity class to help in saving and retrieving the different movie types in our enum class.
    @PrimaryKey(autoGenerate = false)
    val id: Int,

)
