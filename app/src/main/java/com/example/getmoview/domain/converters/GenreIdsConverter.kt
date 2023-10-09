package com.example.getmoview.domain.converters

import androidx.room.TypeConverter

class GenreIdsConverter {
    @TypeConverter
    fun fromGenreIdToString(genreId: List<Int>?): String? {
        return genreId?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToGenreId(genreIdsString: String?): List<Int>? {
        return genreIdsString?.split(",")?.mapNotNull { it.toIntOrNull() }
    }
}