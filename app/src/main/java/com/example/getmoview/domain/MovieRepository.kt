package com.example.getmoview.domain

import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.popular_top_rated.SearchedDto
import com.example.getmoview.domain.model.trending.Trending

interface MovieRepository {

    // We get the movies from the api, we save to the database before displaying. For data persistance.
    // So all the display will be data from our database.

    suspend fun getLocalPopularMovies(): List<MovieEntity>

    suspend fun getLocalTrendingMovies(): List<TrendingMoviesEntity>

    suspend fun getLocalTopRated(): List<MovieEntity>

    suspend fun savePopularAndTopRated(movieDto: MovieDto, movieType: MovieType)

    suspend fun saveTrendingMovies(trending: Trending)

    suspend fun search(query: String, pageNumber: Int):SearchedDto

    suspend fun getPopularAndTopRatedMovieById(id: Int): MovieEntity

    suspend fun getTrendingMovieById(id: Int): TrendingMoviesEntity
}