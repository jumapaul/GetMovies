package com.example.getmoview.data.remote

import com.example.getmoview.domain.model.MovieDtoItem

interface MovieApi {

    suspend fun getPopularMovies(): List<MovieDtoItem>

    suspend fun getTrendingMovies(): List<MovieDtoItem>

    suspend fun getTopRatedMovies(): List<MovieDtoItem>
}