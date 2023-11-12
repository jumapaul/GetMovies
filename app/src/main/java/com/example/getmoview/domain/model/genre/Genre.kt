package com.example.getmoview.domain.model.genre

import com.example.getmoview.data.local.GenresIdsEntity

data class Genre(
    val id: Int,
    val name: String
) {
    fun toGenreIdsEntity(): GenresIdsEntity {
        return GenresIdsEntity(
            id = id,
            name = name
        )
    }
}