package com.example.getmoview.data.remote

import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.TrendingResults

interface MovieApi {

    suspend fun getPopularMovies(): List<MovieDtoItem>

    suspend fun getTrendingMovies(): List<TrendingResults>

    suspend fun getTopRatedMovies(): List<MovieDtoItem>
}