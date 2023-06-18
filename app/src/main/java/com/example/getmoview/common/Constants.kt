package com.example.getmoview.common

object Constants {
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR = "$BASE_URL/movie/popular"
    const val TRENDING = "$BASE_URL//tv/trending/all/day"
    const val TOP_RATED = "$BASE_URL/movie/top_rated"

    //Database name
    const val DATABASE_NAME = "movie.db"
    const val API_KEY = "6c77eaa627c0764fbdb5b04cc07c1555"
}