package com.example.getmoview.domain.repository

import com.example.getmoview.data.local.GenresIdsEntity
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.model.movies.MovieDto

interface MovieRepository {
    // Suspend functions are coroutine that makes it possible for asynchronous programming
    // Suspend indicates that the method can be paused and resumed later without blocking the threads.
    // This allow other tasks to proceed concurrently.
    // Also, by making the function suspend, they can be called fro within coroutine context.

    // Movies
    suspend fun saveMovieItems(page: Int)

    suspend fun getLocalMovies(): List<MoviesEntity>

    // Upcoming Movies
    suspend fun saveUpcomingMovies(page: Int)
    suspend fun getLocalUpcomingMovies(): List<MoviesEntity>

    // Top Rated Movies
    suspend fun saveTopRatedMovies(page: Int)
    suspend fun getLocalTopRatedMovies(): List<MoviesEntity>


    // Popular Movies
    suspend fun savePopularMovies(page: Int)
    suspend fun getLocalPopularMovies(): List<MoviesEntity>

    suspend fun getLocalMovieById(id: Int): MoviesEntity

    suspend fun search(query: String): MovieDto

    //-----------Shows------------------

    //Shows
    suspend fun saveShows(page: Int)
    suspend fun getShows(): List<ShowsEntity>


    //Popular Shows
    suspend fun savePopularShows(page: Int)
    suspend fun getLocalPopularShows(): List<ShowsEntity>

    //TopRated shows
    suspend fun getLocalTopRatedShows(): List<ShowsEntity>

    suspend fun saveTopRatedShows(page: Int)

    suspend fun getTvShowsById(id: Int): ShowsEntity

    // Genres
    suspend fun saveGenres()
    suspend fun getLocalGenres(): List<GenresIdsEntity>
}