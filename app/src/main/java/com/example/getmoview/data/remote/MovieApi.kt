package com.example.getmoview.data.remote

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.genre.GenreDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.TrendingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieDto
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDto

    @GET("trending/all/day")
    suspend fun getTrendingMovies(): TrendingDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieDto

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") searchTerm: String,
    ): MovieDto

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): MovieDtoItem

    @GET("genre/movie/list")
    suspend fun getMoviesGenre(): GenreDto

    @GET("configuration/languages")
    suspend fun getLanguage(): GenreDto
}