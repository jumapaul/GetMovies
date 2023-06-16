package com.example.getmoview.domain

import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType

interface MovieRepository {

    // We get the movies from the api, we save to the database before displaying. For data persistance.
    // So all the display will be data from our database.
    suspend fun getPopularMovies(): List<MovieEntity>

    suspend fun getTrendingMovies(): List<MovieEntity>

    suspend fun getTopRated(): List<MovieEntity>

    suspend fun saveMovie(movieType: MovieType)
}