package com.example.getmoview.domain.repository

import com.example.getmoview.domain.model.genre.GenreDto
import com.example.getmoview.domain.model.movies.MovieDto
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.top_shows.TvShowDto
import com.example.getmoview.domain.model.top_shows.TvShowItem
import com.example.getmoview.domain.model.trending.TrendingDto
import com.example.getmoview.domain.model.upcoming.UpcomingMoviesDto

interface MovieRepository {
    // Suspend functions are coroutine that makes it possible for asynchronous programming
    // Suspend indicates that the method can be paused and resumed later without blocking the threads.
    // This allow other tasks to proceed concurrently.
    // Also, by making the function suspend, they can be called fro within coroutine context.
    suspend fun getPopularMovies(): MovieDto

    suspend fun getPopularTvShows(): TvShowDto

    suspend fun getTrendingMovies(): TrendingDto

    suspend fun getTopRatedMovies(): MovieDto

    suspend fun getTopRatedTvShows(page: Int): TvShowDto

    suspend fun getUpcomingMovies(page: Int): UpcomingMoviesDto

    suspend fun search(query: String): MovieDto

    suspend fun getMovieById(id: Int): MovieDtoItem

    suspend fun getTvShowsById(id: Int): TvShowItem

    suspend fun getMovies(page: Int): MovieDto

    suspend fun getTvShows(page: Int): TvShowDto

    suspend fun getGenres(): GenreDto
}