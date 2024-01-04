package com.example.getmoview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val genre_ids: List<Int>,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
//    val video: Boolean,
    val vote_average: Double,
    val categoryType: CategoryType,
    var isFavorite: Boolean
)
