package com.example.getmoview.data

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.TrendingDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieApi,
) : MovieRepository {
    // Io dispatchers starts the coroutine tin the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.

    override suspend fun getPopularMovies(): MovieDto {
        return api.getPopularMovies()
    }

    override suspend fun getTrendingMovies(): TrendingDto {
        return withContext(Dispatchers.IO) {
            api.getTrendingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieDto {
        return withContext(Dispatchers.IO) {
            api.getTopRatedMovies()
        }
    }

    override suspend fun search(query: String): MovieDto {
        return withContext(Dispatchers.IO) {
            api.searchMovie(query)
        }
    }

    override suspend fun getMovieById(id: Int): MovieDtoItem {
        return withContext(Dispatchers.IO) {
            api.getMovieById(id)
        }
    }

    override suspend fun getMovies(page: Int): MovieDto {
         return  withContext(Dispatchers.IO){
             api.getMovies(page)
         }

    }
}