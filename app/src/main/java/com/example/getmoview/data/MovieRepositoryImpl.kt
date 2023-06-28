package com.example.getmoview.data

import android.util.Log
import com.example.getmoview.data.local.MovieDao
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import com.example.getmoview.domain.model.popular_top_rated.MovieDto
import com.example.getmoview.domain.model.trending.Trending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val dao: MovieDao,
    private val api: MovieApi
) : MovieRepository {

    // Io dispatchers starts the coroutine tin the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.
    override suspend fun getLocalPopularMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllMovies().filter { it.movieType == MovieType.POPULAR.name }
        }
    }

    override suspend fun getLocalTrendingMovies(): List<TrendingMoviesEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllTrendingMovies()
        }
    }

    override suspend fun getLocalTopRated(): List<MovieEntity> {

        return withContext(Dispatchers.IO) {
            dao.getAllMovies().filter { it.movieType == MovieType.TOP_RATED.name }
        }
    }

    override suspend fun savePopularAndTopRated(movieDto: MovieDto, movieType: MovieType) {
        withContext(Dispatchers.IO) {
            dao.addMovie(movieDto.results.map { it.toMovieEntity(movieType) })
        }
    }
    override suspend fun saveTrendingMovies(trending: Trending) {
        withContext(Dispatchers.IO){

            dao.addTrendingMovies(trending.results.map { it.toTrendingEntity() })
        }
    }
}