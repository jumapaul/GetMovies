package com.example.getmoview.data

import com.example.getmoview.data.local.MovieDao
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val dao: MovieDao,
    private val api: MovieApi
) : MovieRepository {

    // Io dispatchers starts the coroutine tin the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO){
            dao.getAllMovies().filter { it.movieType == MovieType.POPULAR.name }
        }
    }

    override suspend fun getTrendingMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO){
            dao.getAllMovies().filter { it.movieType == MovieType.TRENDING.name }
        }
    }

    override suspend fun getTopRated(): List<MovieEntity> {

        return withContext(Dispatchers.IO){
            dao.getAllMovies().filter { it.movieType == MovieType.TOP_RATED.name }
        }
    }

    override suspend fun saveMovie(movieType: MovieType) {
       //Here, we get the data from the api and save it to our database
        val popularData = api.getPopularMovies() // Retrieving popular data from the api
        val trendingData = api.getTrendingMovies() // Retrieving trending data
        val top_rated = api.getTopRatedMovies() // Retrieving top_rated data
        withContext(Dispatchers.IO){
            dao.addMovie(popularData.map { it.toMovieEntity(movieType) }) // Saving the popular data to the database
            dao.addMovie(trendingData.map { it.toMovieEntity(movieType) }) // Saving trending data to the db
            dao.addMovie(top_rated.map { it.toMovieEntity(movieType) }) // Saving top rated data.
        }

    }
}