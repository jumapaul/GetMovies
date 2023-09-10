package com.example.getmoview.data.remote

import com.example.getmoview.domain.model.genre.GenreDto
import com.example.getmoview.domain.model.movies.MovieDto
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.top_rated_shows.TvShowDto
import com.example.getmoview.domain.model.top_rated_shows.TvShowItem
import com.example.getmoview.domain.model.trending.TrendingDto
import com.example.getmoview.domain.model.upcoming.UpcomingMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int): MovieDto

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDto

    @GET("tv/popular")
    suspend fun getPopularTvShows(): TvShowDto

    @GET("trending/all/day")
    suspend fun getTrendingMovies(): TrendingDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("page") page: Int): TvShowDto

    @GET("tv/{series_id}")
    suspend fun getShowsById(@Path("series_id") showsId: Int): TvShowItem

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): UpcomingMoviesDto

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") searchTerm: String): MovieDto

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDtoItem

    @GET("genre/movie/list")
    suspend fun getMoviesGenre(): GenreDto


    @GET("configuration/languages")
    suspend fun getLanguage(): GenreDto
}