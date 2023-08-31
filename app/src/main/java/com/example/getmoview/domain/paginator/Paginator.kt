package com.example.getmoview.domain.paginator

interface Paginator<Key, MovieDtoItem> {
    suspend fun loadNextItem()

    suspend fun reset()
}