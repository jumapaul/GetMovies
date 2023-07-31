package com.example.getmoview.domain.model

interface Paginator<Key, MovieDtoItem> {
    suspend fun loadNextItem()

    suspend fun reset()
}