package com.example.getmoview.data.remote

import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.trending.Trending
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDto

    @GET("trending/all/day")
    suspend fun getTrendingMovies(): Trending

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieDto
}