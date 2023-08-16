package com.example.getmoview.domain.repository

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.genre.GenreDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.TrendingDto

interface MovieRepository {
    // Suspend functions are coroutine that makes it possible for asynchronous programming
    // Suspend indicates that the method can be paused and resumed later without blocking the threads.
    // This allow other tasks to proceed concurrently.
    // Also, by making the function suspend, they can be called fro within coroutine context.
    suspend fun getPopularMovies(): MovieDto

    suspend fun getTrendingMovies(): TrendingDto

    suspend fun getTopRatedMovies(): MovieDto

    suspend fun search(query: String): MovieDto

    suspend fun getMovieById(id: Int): MovieDtoItem

    suspend fun getMovies(page: Int): MovieDto

    suspend fun getGenres(): GenreDto
}