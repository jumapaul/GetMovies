package com.example.getmoview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val name: String,
    val overview: String,
    val poster_path: String,
    val vote_average: Double,
    val categoryType: CategoryType
)
