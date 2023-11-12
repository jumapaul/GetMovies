package com.example.getmoview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genres")
data class GenresIdsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
