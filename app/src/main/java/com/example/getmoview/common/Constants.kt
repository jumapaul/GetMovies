package com.example.getmoview.common

object Constants {
    const val API_KEY = "6c77eaa627c0764fbdb5b04cc07c1555"
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val POPULAR = "$BASE_URL/movie/popular?api_key=$API_KEY"
    const val TRENDING = "$BASE_URL//tv/trending/all/day?api_key=$API_KEY"
    const val TOP_RATED = "$BASE_URL/movie/top_rated?api_key=$API_KEY"

    //Database name
    const val DATABASE_NAME = "movie.db"

//    ?api_key=6c77eaa627c0764fbdb5b04cc07c1555
}