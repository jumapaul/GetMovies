package com.example.getmoview.data.remote

import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.popular_top_rated.SearchedDto
import com.example.getmoview.domain.model.trending.Trending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDto

    @GET("trending/all/day")
    suspend fun getTrendingMovies(): Trending

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieDto

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") searchTerm: String,
        @Query("page") pageNumber: Int = 1
    ): SearchedDto
}